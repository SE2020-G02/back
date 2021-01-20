package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Blogs;
import com.seg02.oursudoku.info.BlogInfo;
import com.seg02.oursudoku.info.BlogsInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.mapper.BlogsMapper;
import com.seg02.oursudoku.service.IBlogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
@Service
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs> implements IBlogsService {

	@Resource
	BlogsMapper blogsMapper;

	@Resource
	AccountMapper accountMapper;

	@Override
	public BlogInfo getByPid(String Pid) {
		Blogs blogs = blogsMapper.selectByPid(Pid);
		if (blogs != null) {
			return blogs.change();
		} else {
			return null;
		}
	}

	@Override
	public BlogInfo getByBid(String Bid) {
		Blogs blogs = blogsMapper.selectByBid(Bid);
		if (blogs != null) {
			return blogs.change();
		} else {
			return null;
		}
	}

	@Override
	public List<String> getAll(String Pid) {
		return blogsMapper.selectAllByPid(Pid);
	}

	@Override
	public String getAid(String Bid) {
		return blogsMapper.selectAid(Bid);
	}

	@Override
	public BlogsInfo getBlogs(String Bid) {
		BlogsInfo ans = new BlogsInfo();
		ans.setBlogId(Bid);
		ans.setProblemId(blogsMapper.selectPid(Bid));
		ans.setAccountNickname(accountMapper.selectNameById(blogsMapper.selectAid(Bid)));
		return ans;
	}

	@Override
	public BlogInfo creatBlog(String Aid, String Pid, String Message) {
		BlogInfo blogInfo = new BlogInfo();
		blogInfo.setBlogId(String.format("%04d", blogsMapper.selectMaxBid() + 1));
		blogInfo.setBloBlogId(blogInfo.getBlogId());
		blogInfo.setProblemId(Pid);
		blogInfo.setBlogMessage(Message);
		blogsMapper.insertBlog(blogInfo.getBlogId(), "0000", blogInfo.getProblemId(), blogInfo.getBlogMessage());
		Blogs blogs = blogsMapper.selectByBid(blogInfo.getBlogId());
		if (blogs.getProblemId().equals(Pid)) {
			blogsMapper.insertBlogRelation(blogs.getBlogId(), Aid, "编写", "正常");
			return blogs.change();
		} else {
			return null;
		}
	}
}
