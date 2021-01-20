package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.info.AdministratorInfo;
import com.seg02.oursudoku.req.AdministratorReq;
import com.seg02.oursudoku.service.IAdministratorService;
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
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	IAdministratorService iAdministratorService;

	@CrossOrigin
	@PostMapping("/login")
	public ResultBean<Boolean> login(@RequestBody AdministratorReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		res.setData(iAdministratorService.login(req.getAdminId(), req.getAdminPwd()));
		if (res.getData()) {
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("登录成功");
		} else {
			res.setCode(ResultBean.FAIL);
			res.setMsg("密码错误");
		}
		return res;
	}

}
