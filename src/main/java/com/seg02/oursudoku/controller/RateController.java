package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.RateLimitReq;
import com.seg02.oursudoku.req.RatePageCountReq;
import com.seg02.oursudoku.res.RateLimitRes;
import com.seg02.oursudoku.res.RatePageCountRes;
import com.seg02.oursudoku.res.RateStaticRes;
import com.seg02.oursudoku.service.IRateService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@RestController
@RequestMapping("/rate")
public class RateController {

	@Autowired
	IRateService iRateService;

	@CrossOrigin
	@PostMapping("/limit")
	public ResultBean<RateLimitRes> rateLimit() {
		RateLimitRes rateLimitRes = new RateLimitRes();
		rateLimitRes.setRateInfoList(iRateService.getByCount());
		ResultBean<RateLimitRes> res = new ResultBean<RateLimitRes>();
		res.setData(rateLimitRes);
		res.setMsg("获取成功");
		res.setCode(ResultBean.SUCCESS);
		return res;
	}

//	@CrossOrigin
//	@PostMapping("/pageCount")
//	public ResultBean<RatePageCountRes> pageCount(@RequestBody RatePageCountReq req) {
//		RatePageCountRes ratePageCountRes = new RatePageCountRes();
//		ratePageCountRes.setPageCount(iRateService.getPageCount(req.getRateCount()));
//		ResultBean<RatePageCountRes> res = new ResultBean<RatePageCountRes>();
//		res.setData(ratePageCountRes);
//		res.setMsg("查询成功");
//		res.setCode(ResultBean.FAIL);
//		return res;
//	}

	@CrossOrigin
	@PostMapping("/static")
	public ResultBean<RateStaticRes> staticMessage() {
		ResultBean<RateStaticRes> res = new ResultBean<RateStaticRes>();
		RateStaticRes rateStaticRes = iRateService.getStaticMessage();
		res.setData(rateStaticRes);
		res.setMsg("获取成功");
		res.setCode(ResultBean.SUCCESS);
		return res;
	}
}
