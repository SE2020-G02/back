package com.seg02.oursudoku.controller;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.req.SolveJudgeReq;
import com.seg02.oursudoku.req.SolveRankReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SolveControllerTest {

	@Resource
	SolveController solveController;

	@Test
	public void solveRank() {
		SolveRankReq req = new SolveRankReq();
		req.setProblemId("0156");

		System.out.println(solveController.solveRank(req).toString());
	}

	@Test
	public void solveJudge(){
		SolveJudgeReq req = new SolveJudgeReq();
		req.setAccountId("1");
		req.setProblemId("0523");

		solveController.solveJudge(req);

	}
}