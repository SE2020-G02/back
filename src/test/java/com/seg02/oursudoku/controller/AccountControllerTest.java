package com.seg02.oursudoku.controller;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.req.AccountLoginReq;
import com.seg02.oursudoku.req.AccountRegisterReq;
import com.seg02.oursudoku.req.AccountUpdatePwdReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Test.class)

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

	@Resource
	private AccountController accountController;

	@Test
	public void login() {
		AccountLoginReq req = new AccountLoginReq();
		req.setAccountNickname("hahaha");
		req.setAccountPwd("pwd");

		System.out.println(accountController.login(req));
	}

	@Test
	public void register() {
		AccountRegisterReq req = new AccountRegisterReq();
		req.setAccountNickname("dhg");
		req.setAccountPhone("15893888870");
		req.setAccountPwd("zhangxin123.");

		System.out.println(accountController.register(req));

	}

	@Test
	public void updatePwd() {
		AccountUpdatePwdReq req = new AccountUpdatePwdReq();
		req.setAccountNickname("2");
		req.setAccountOldPwd("zhangxin123.");
		req.setAccountNewPwd("pwd");

		System.out.println(accountController.updatePwd(req));
	}
}