package com.seg02.oursudoku.controller;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.req.ProblemChooseReq;
import com.seg02.oursudoku.req.ProblemsInsertReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProblemControllerTest {

	@Resource
	private ProblemController problemController;

	@Test
	public void problemPage() {
		ProblemChooseReq req = new ProblemChooseReq();
		req.setAccountId("1");
		req.setProblemCount(2);
		req.setProblemLevel(1);

		for (int i = 0; i < 10; i++) {
			System.out.println(problemController.problemPage(req));
		}


	}

	@Test
	public void insertProblems() {
		ProblemsInsertReq req = new ProblemsInsertReq();
		req.setPath("C:\\Users\\ibm\\Desktop\\oursudoku\\src\\main\\java\\com\\seg02\\oursudoku\\util\\level\\level7.txt");
		req.setProblemLevel(7);

		System.out.println(problemController.insertProblems(req));

	}
}