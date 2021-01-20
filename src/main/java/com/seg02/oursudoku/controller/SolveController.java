package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.entity.Solve;
import com.seg02.oursudoku.info.SolveInfo;
import com.seg02.oursudoku.info.SolveRankInfo;
import com.seg02.oursudoku.req.SolveJudgeReq;
import com.seg02.oursudoku.req.SolveRankReq;
import com.seg02.oursudoku.req.SolveSubmitReq;
import com.seg02.oursudoku.res.SolveRankRes;
import com.seg02.oursudoku.service.IAccountService;
import com.seg02.oursudoku.service.ISolveService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/solve")
public class SolveController {

	@Autowired
	ISolveService iSolveService;

	@Autowired
	IAccountService iAccountService;

	@CrossOrigin
	@PostMapping("/rank")
	public ResultBean<SolveRankRes> solveRank(@RequestBody SolveRankReq req) {
		List<Solve> solveList = iSolveService.getRank(req.getProblemId());

		List<SolveRankInfo> SolveRankInfos = new ArrayList<SolveRankInfo>();
		for (int i = 0; i < solveList.size(); i++) {
			SolveRankInfo solveRankInfo = new SolveRankInfo();
			solveRankInfo.setRank(i + 1);
			solveRankInfo.setAccountNickname(iAccountService.findNameById(solveList.get(i).getAccountId()));
			solveRankInfo.setSolveCostTime(solveList.get(i).changeTime());
			SolveRankInfos.add(solveRankInfo);
		}

		SolveRankRes solveRankRes = new SolveRankRes();
		solveRankRes.setSolveCount(SolveRankInfos.size());
		solveRankRes.setSolveRankInfoList(SolveRankInfos);

		ResultBean<SolveRankRes> res = new ResultBean<SolveRankRes>();
		res.setData(solveRankRes);
		res.setMsg("查询成功");
		res.setCode(ResultBean.SUCCESS);

		return res;
	}

	@CrossOrigin
	@PostMapping("/submit")
	public ResultBean<Boolean> solveSubmit(@RequestBody SolveSubmitReq req) {
		ResultBean<Boolean> ans = new ResultBean<Boolean>();
		if (req.cmp()) {
			SolveInfo solveInfo = new SolveInfo();
			solveInfo.setProblemId(req.getProblemId());
			solveInfo.setAccountId(req.getAccountId());
			solveInfo.setSolveCostTime(req.getSolveCostTime());

			if (!iSolveService.solveJudge(req.getAccountId(), req.getProblemId())) {
				ans.setData(iSolveService.submitSolve(solveInfo));
			}
			ans.setMsg("答案正确");
			ans.setCode(ResultBean.SUCCESS);
		} else {
			ans.setData(null);
			ans.setMsg("答案错误");
			ans.setCode(ResultBean.FAIL);
		}

		return ans;
	}

	@CrossOrigin
	@PostMapping("/judge")
	public ResultBean<Boolean> solveJudge(@RequestBody SolveJudgeReq req) {
		ResultBean<Boolean> ans = new ResultBean<Boolean>();
		if (iSolveService.solveJudge(req.getAccountId(), req.getProblemId())) {
			ans.setData(true);
		} else {
			ans.setData(false);
		}
		ans.setMsg("查询成功");
		ans.setCode(ResultBean.SUCCESS);
		return ans;
	}
}
