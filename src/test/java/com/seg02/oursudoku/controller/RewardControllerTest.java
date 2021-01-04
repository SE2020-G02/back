package com.seg02.oursudoku.controller;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.req.RewardPageReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardControllerTest {

	@Resource
	RewardController rewardController;

	@Test
	public void rewardPage() {
		RewardPageReq req = new RewardPageReq();
		req.setRewardCount(2);
		req.setRewardState("未做出");

		for (int i = 0; i < 10; i++) {
			System.out.println(rewardController.rewardPage(req).toString());
		}
	}
}