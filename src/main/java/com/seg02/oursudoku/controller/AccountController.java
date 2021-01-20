package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.entity.Account;
import com.seg02.oursudoku.entity.AccountPublic;
import com.seg02.oursudoku.info.AccountInfo;
import com.seg02.oursudoku.req.*;
import com.seg02.oursudoku.res.AccountPublicMessageRes;
import com.seg02.oursudoku.res.AccountRes;
import com.seg02.oursudoku.service.IAccountService;
import com.seg02.oursudoku.util.MD5Util;
import com.seg02.oursudoku.util.MyBeanUtils;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
@RestController
@RequestMapping("/account")
public class AccountController {


	@Autowired
	IAccountService iAccountService;

	/**
	 * 用户登录接口
	 * 根据参数，在数据库中查找用户，并核对密码
	 *
	 * @param req:用户id+用户密码
	 * @return：登录信息+用户信息
	 */
	@CrossOrigin
	@PostMapping("/login")
	public ResultBean<AccountRes> login(@RequestBody AccountLoginReq req) {
		AccountRes account = new AccountRes();
		account.setAccountInfo(iAccountService.findByNickname(req.getAccountNickname()));
		ResultBean<AccountRes> result = new ResultBean<AccountRes>();
		if (account.getAccountInfo() != null) {
			if (account.getAccountInfo().getAccountPwd().equals(MD5Util.md5(req.getAccountPwd()))) {
				result.setData(account);
				result.setMsg("登录成功");
			} else {
				result.setMsg("密码错误");
				result.setCode(ResultBean.FAIL);
			}
		} else {
			result.setMsg("用户不存在");
			result.setCode(ResultBean.FAIL);
			result.setData(null);
		}
		return result;
	}

	/**
	 * 用户主页接口
	 * 首先判断用户名是否存在，判断用户手机号是否已注册，然后新建用户信息并自动获取用户id，新添用户，返回信息
	 *
	 * @param req：用户昵称+用户手机号+用户密码
	 * @return：注册信息+用户信息
	 */
	@CrossOrigin
	@PostMapping("/register")
	public ResultBean<AccountRes> register(@RequestBody AccountRegisterReq req) {
//		synchronized (this) {
			ResultBean<AccountRes> res = new ResultBean<AccountRes>();
			if (iAccountService.findByNickname(req.getAccountNickname()) == null) {
				if (iAccountService.findPhone(req.getAccountPhone())) {
					AccountInfo accountInfo = new AccountInfo();
					accountInfo.setAccountId(iAccountService.findAID());
					accountInfo.setAccountPwd(MD5Util.md5(req.getAccountPwd()));
					accountInfo.setAccountNickname(req.getAccountNickname());
					accountInfo.setAccountPhone(req.getAccountPhone());
					accountInfo.setAccountSignature("这个人很懒，什么都没写");
					accountInfo.setAccountSex("男");
					accountInfo.setAccountLevel(1);

					AccountRes accountRes = new AccountRes();
					accountRes.setAccountInfo(accountInfo);
					res.setData(accountRes);
					if (iAccountService.insertAccount(accountInfo)) {
						res.setMsg("注册成功");
						res.setCode(ResultBean.SUCCESS);
					} else {
						res.setMsg("注册失败");
						res.setCode(ResultBean.FAIL);
					}
				} else {
					res.setData(null);
					res.setCode(ResultBean.FAIL);
					res.setMsg("手机号已注册");
				}
			} else {
				res.setMsg("用户名已存在");
				res.setCode(ResultBean.FAIL);
				res.setData(null);
			}
			return res;
//		}
	}

	/**
	 * 更新密码接口
	 * 根据用户昵称查找用户，核对密码是否正确，修改用户密码
	 *
	 * @param req：用户id+旧密码+新密码
	 * @return：修改信息+用户信息
	 */
	@CrossOrigin
	@PostMapping("/updatePwd")
	public ResultBean<AccountRes> updatePwd(@RequestBody AccountUpdatePwdReq req) {
		ResultBean<AccountRes> res = new ResultBean<AccountRes>();

		AccountInfo accountInfo = iAccountService.findByNickname(req.getAccountNickname());
		if (accountInfo != null) {
			req.setAccountOldPwd(MD5Util.md5(req.getAccountOldPwd()));
			if (req.getAccountOldPwd().equals(accountInfo.getAccountPwd())) {
				accountInfo.setAccountPwd(MD5Util.md5(req.getAccountNewPwd()));
				Account account = new Account();
				MyBeanUtils.copyProperties(accountInfo, account);
				if (iAccountService.updateAccountPwd(new AccountInfo(account))) {
					AccountRes accountRes = new AccountRes();
					accountRes.setAccountInfo(accountInfo);
					res.setData(accountRes);
					res.setMsg("修改成功");
					res.setCode(ResultBean.SUCCESS);
				} else {
					res.setData(null);
					res.setMsg("修改失败");
					res.setCode(ResultBean.FAIL);
				}
			} else {
				res.setData(null);
				res.setMsg("密码错误");
				res.setCode(ResultBean.FAIL);
			}
		} else {
			res.setMsg("用户不存在");
			res.setCode(ResultBean.FAIL);
			res.setData(null);
		}
		return res;
	}

	@CrossOrigin
	@PostMapping("/public/message")
	public ResultBean<AccountPublicMessageRes> getPublicMessage(@RequestBody AccountPublicMessageReq req) {
		AccountPublic accountPublic = iAccountService.findPublicMessage(req.getAccountId());
		AccountPublicMessageRes accountPublicMessageRes = new AccountPublicMessageRes();
		accountPublicMessageRes.setAccountNickname(accountPublic.getAccountNickname());
		accountPublicMessageRes.setAccountProfilePhoto(accountPublic.getAccountProfilePhoto());
		accountPublicMessageRes.setAccountSignature(accountPublic.getAccountSignature());
		accountPublicMessageRes.setAccountLevel(accountPublic.getAccountLevel());
		ResultBean<AccountPublicMessageRes> res = new ResultBean<AccountPublicMessageRes>();
		res.setData(accountPublicMessageRes);
		res.setMsg("获取成功");
		res.setCode(ResultBean.SUCCESS);
		return res;
	}

	@CrossOrigin
	@PostMapping("/reset")
	public ResultBean<Boolean> resetPwd(@RequestBody ResetPwdReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();

		AccountInfo accountInfo = iAccountService.findByNickname(req.getAccountNickname());
		if (accountInfo != null) {
			if (accountInfo.getAccountPhone().equals(req.getAccountPhone())) {
				accountInfo.setAccountPwd(MD5Util.md5("000000"));
				res.setData(iAccountService.updateAccountPwd(accountInfo));
				if (res.getData()) {
					res.setCode(ResultBean.SUCCESS);
					res.setMsg("修改成功");
				} else {
					res.setCode(ResultBean.FAIL);
					res.setMsg("修改失败");
				}
			} else {
				res.setData(false);
				res.setCode(ResultBean.FAIL);
				res.setMsg("手机号错误");
			}
		} else {
			res.setData(false);
			res.setCode(ResultBean.FAIL);
			;
			res.setMsg("用户未找到");
		}
		return res;
	}
}
