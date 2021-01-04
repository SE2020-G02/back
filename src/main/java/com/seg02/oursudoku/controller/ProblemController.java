package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.info.ProblemInfo;
import com.seg02.oursudoku.req.AccountPublicMessageReq;
import com.seg02.oursudoku.req.ProblemChooseReq;
import com.seg02.oursudoku.req.ProblemMessageReq;
import com.seg02.oursudoku.req.ProblemsInsertReq;
import com.seg02.oursudoku.res.ProblemPageRes;
import com.seg02.oursudoku.res.ProblemRandomRes;
import com.seg02.oursudoku.res.ProblemRes;
import com.seg02.oursudoku.service.IProblemService;
import com.seg02.oursudoku.service.ISolveService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	IProblemService iProblemService;

	@Autowired
	ISolveService iSolveService;

	@PostMapping("/page")
	public ResultBean<ProblemPageRes> problemPage(@RequestBody ProblemChooseReq req) {
		ResultBean<ProblemPageRes> res = new ResultBean<ProblemPageRes>();
		ProblemPageRes problemPageRes = new ProblemPageRes();
		problemPageRes.setProblemInfoList(iProblemService.findByLevel(req.getProblemLevel(), req.getProblemCount()));
		res.setData(problemPageRes);
		res.setMsg("查询成功");
		res.setCode(ResultBean.SUCCESS);
		return res;
	}

	@PostMapping("/insert/problems")
	public ResultBean<Boolean> insertProblems(@RequestBody ProblemsInsertReq req) {
		Boolean ans = iProblemService.insertProblems(req.getPath(), req.getProblemLevel());
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		res.setData(ans);
		if (ans) {
			res.setMsg("新增成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("新增失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/message")
	public ResultBean<ProblemRes> getMessage(@RequestBody ProblemMessageReq req) {
		ResultBean<ProblemRes> res = new ResultBean<ProblemRes>();
		ProblemRes problemRes = new ProblemRes();
		ProblemInfo problemInfo = iProblemService.findByPid(String.format("%04d", Integer.parseInt(req.getProblemId())));
		problemInfo.setProblemSolveCount(iSolveService.getSolveCount(String.format("%04d", Integer.parseInt(req.getProblemId()))));
		problemRes.setProblemInfo(problemInfo);
		res.setData(problemRes);
		if (res.getData() != null) {
			res.setMsg("获取成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("获取失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/random")
	public ResultBean<ProblemRandomRes> getRandom(@RequestBody AccountPublicMessageReq req) {
		ProblemRandomRes problemRandomRes = new ProblemRandomRes();
		problemRandomRes.setProblemInfo(iProblemService.findRandom(req.getAccountId()));
		problemRandomRes.setSolveTime(iSolveService.getMinSolveTime(problemRandomRes.getProblemInfo().getProblemId()));
		ResultBean<ProblemRandomRes> res = new ResultBean<ProblemRandomRes>();
		res.setData(problemRandomRes);
		res.setMsg("获取成功");
		res.setCode(ResultBean.SUCCESS);
		return res;

	}
}
