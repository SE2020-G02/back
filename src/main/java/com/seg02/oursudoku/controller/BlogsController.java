package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.entity.AccountPublic;
import com.seg02.oursudoku.info.BlogInfo;
import com.seg02.oursudoku.info.BlogsInfo;
import com.seg02.oursudoku.req.BlogCreatReq;
import com.seg02.oursudoku.req.BlogReq;
import com.seg02.oursudoku.req.ProblemMessageReq;
import com.seg02.oursudoku.res.BlogCreatRes;
import com.seg02.oursudoku.res.BlogRes;
import com.seg02.oursudoku.res.BlogsRes;
import com.seg02.oursudoku.service.IAccountService;
import com.seg02.oursudoku.service.IBlogsService;
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
 * @since 2020-12-31
 */
@RestController
@RequestMapping("/blogs")
public class BlogsController {

	@Autowired
	IBlogsService iBlogsService;

	@Autowired
	IAccountService iAccountService;

	@CrossOrigin
	@PostMapping("/display")
	public ResultBean<BlogRes> getBlog(@RequestBody BlogReq req) {
		ResultBean<BlogRes> res = new ResultBean<BlogRes>();

		BlogRes blogRes = new BlogRes();
		if (req.getBlogId().equals("0000")) {
			blogRes.setBlogInfo(iBlogsService.getByPid(req.getProblemId()));
		} else {
			blogRes.setBlogInfo(iBlogsService.getByBid(req.getBlogId()));
		}
		if (blogRes.getBlogInfo() != null) {
			AccountPublic accountPublic = iAccountService.findPublicMessage(iBlogsService.getAid(blogRes.getBlogInfo().getBlogId()));
			if (accountPublic != null) {
				blogRes.setAccountPublicInfo(accountPublic.change());
				res.setData(blogRes);
				res.setCode(ResultBean.SUCCESS);
				res.setMsg("获取成功");
			} else {
				res.setData(null);
				res.setCode(ResultBean.FAIL);
				res.setMsg("用户信息获取失败");

			}
		} else {
			res.setData(null);
			res.setCode(ResultBean.FAIL);
			res.setMsg("解法信息获取失败");
		}
		return res;
	}

	@CrossOrigin
	@PostMapping("/all")
	public ResultBean<BlogsRes> getAllBlog(@RequestBody ProblemMessageReq req) {
		ResultBean<BlogsRes> res = new ResultBean<BlogsRes>();

		BlogsRes blogsRes = new BlogsRes();

		List<String> blogsId = iBlogsService.getAll(req.getProblemId());
		if (blogsId != null) {
			List<BlogsInfo> blogsInfoList = new ArrayList<BlogsInfo>();
			for (int i = 0; i < blogsId.size(); i++) {
				blogsInfoList.add(iBlogsService.getBlogs(blogsId.get(i)));
			}
			blogsRes.setBlogsInfoList(blogsInfoList);
			res.setData(blogsRes);
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("获取成功");
		} else {
			res.setData(null);
			res.setCode(ResultBean.FAIL);
			res.setMsg("本题暂无更多题解");
		}
		return res;
	}

	@CrossOrigin
	@PostMapping("/creat")
	public ResultBean<BlogCreatRes> creatBlog(@RequestBody BlogCreatReq req) {
		ResultBean<BlogCreatRes> res = new ResultBean<BlogCreatRes>();
		BlogInfo blogInfo = iBlogsService.creatBlog(req.getAccountId(), req.getProblemId(), req.getBlogMessage());
		if (blogInfo != null) {
			BlogCreatRes blogCreatRes = new BlogCreatRes();
			blogCreatRes.setBlogId(blogInfo.getBlogId());
			res.setData(blogCreatRes);
			res.setCode(ResultBean.SUCCESS);
			res.setMsg("创建成功");
		} else {
			res.setData(null);
			res.setCode(ResultBean.FAIL);
			res.setMsg("创建失败");
		}
		return res;
	}
}
