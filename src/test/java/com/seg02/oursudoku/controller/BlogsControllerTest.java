package com.seg02.oursudoku.controller;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.req.BlogCreatReq;
import com.seg02.oursudoku.req.BlogReq;
import com.seg02.oursudoku.res.BlogRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogsControllerTest {

	@Resource
	BlogsController blogsController;

	@Test
	public void getBlog() {
		BlogReq req = new BlogReq();
		req.setBlogId("0000");
		req.setProblemId("0001");

		System.out.println(blogsController.getBlog(req).toString());

	}

	@Test
	public void getAllBlog() {
	}

	@Test
	public void creatBlog() {
		BlogCreatReq req = new BlogCreatReq();
		req.setAccountId("1");
		req.setBlogMessage("jtest test1");
		req.setProblemId("0001");

		System.out.println(blogsController.creatBlog(req));
	}
}