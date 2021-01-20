package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.entity.UserAppeal;
import com.seg02.oursudoku.req.AppealAllReq;
import com.seg02.oursudoku.req.UserAppealReq;
import com.seg02.oursudoku.req.UserApprelIdReq;
import com.seg02.oursudoku.res.UserAppealsRes;
import com.seg02.oursudoku.service.IUserAppealService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/appeal")
public class UserAppealController {

	@Autowired
	IUserAppealService iUserAppealService;

	@CrossOrigin
	@PostMapping("/submit")
	public ResultBean<Boolean> submit(@RequestBody UserAppealReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();

		UserAppeal userAppeal = new UserAppeal();
		userAppeal.setAccountId(req.getAccountId());
		userAppeal.setAppealMessage(req.getAppealMessage());
		userAppeal.setAppealState("待处理");
		userAppeal.setAppealType(req.getAppealType());
		userAppeal.setAppealTypeId(req.getAppealTypeId());

		res.setData(iUserAppealService.submit(userAppeal));
		if (res.getData()) {
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("申诉成功");
		} else {
			res.setCode(ResultBean.FAIL);
			res.setMsg("申诉失败");
		}
		return res;
	}

	@CrossOrigin
	@PostMapping("/all")
	public ResultBean<UserAppealsRes> all(@RequestBody AppealAllReq req) {
		ResultBean<UserAppealsRes> res = new ResultBean<UserAppealsRes>();

		UserAppealsRes userAppealsRes = new UserAppealsRes();
		userAppealsRes.setUserAppealInfoLis(iUserAppealService.getAll(req.getState()));
		res.setData(userAppealsRes);
		res.setCode(ResultBean.SUCCESS);
		res.setMsg("获取成功");
		return res;
	}

	@CrossOrigin
	@PostMapping("/action")
	public ResultBean<Boolean> action(@RequestBody UserApprelIdReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		if (req.getAction().equals("通过")) {
			res.setData(iUserAppealService.action(req.getAppealId()));
		} else {
			res.setData(iUserAppealService.down(req.getAppealId()));
		}
		if (res.getData()) {
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("操作成功");
		} else {
			res.setCode(ResultBean.FAIL);
			res.setMsg("操作失败");
		}
		return res;
	}
}
