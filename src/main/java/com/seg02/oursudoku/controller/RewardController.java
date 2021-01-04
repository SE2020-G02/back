package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.*;
import com.seg02.oursudoku.res.RewardRes;
import com.seg02.oursudoku.service.IProblemService;
import com.seg02.oursudoku.service.IRewardService;
import com.seg02.oursudoku.util.ProblemUtil;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
@RestController
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	IRewardService iRewardService;

	@Autowired
	IProblemService iProblemService;

	@PostMapping("/page")
	public ResultBean<RewardRes> rewardPage(@RequestBody RewardPageReq req) {
		RewardRes rewardRes = new RewardRes();
		rewardRes.setRewardInfoList(iRewardService.findByState(req.getRewardState(), req.getRewardCount()));
		ResultBean<RewardRes> res = new ResultBean<RewardRes>();
		res.setData(rewardRes);
		if (res.getData() != null) {
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("获取成功");
		} else {
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("获取失败");
		}
		return res;
	}


	@PostMapping("/judge")
	public ResultBean<Boolean> judgeReward(@RequestBody RewardJudgeReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		res.setData(ProblemUtil.judge(req.getProblemPanes()));
		res.setCode(ResultBean.SUCCESS);
		res.setMsg("判断成功");
		return res;
	}

	@PostMapping("/creat")
	public ResultBean<Boolean> creatReward(@RequestBody RewardCreatReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();

		if (ProblemUtil.judge(req.getProblemInfo().getProblemPanes())) {
			if (iProblemService.creatProblem(req.getProblemInfo())) {
				if (iRewardService.creatReward(req.getRewardInfo())) {
					res.setData(true);
					res.setCode(ResultBean.SUCCESS);
					res.setMsg("新建成功");
				} else {
					res.setData(false);
					res.setCode(ResultBean.FAIL);
					res.setMsg("新建悬赏失败");
				}
			} else {
				res.setData(false);
				res.setCode(ResultBean.FAIL);
				res.setMsg("新建题目失败");
			}
		} else {
			res.setData(false);
			res.setCode(ResultBean.FAIL);
			res.setMsg("题目无唯一解");
		}
		return res;
	}
}
