package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Blogs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.BlogInfo;
import com.seg02.oursudoku.info.BlogsInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
public interface IBlogsService extends IService<Blogs> {

	public BlogInfo getByPid(String Pid);

	public BlogInfo getByBid(String Bid);

	public List<String> getAll(String Pid);

	public String getAid(String Bid);

	public BlogsInfo getBlogs(String Bid);

	public BlogInfo creatBlog(String Aid, String Pid, String Message);
}
