package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.OursudokuApplication;
import com.seg02.oursudoku.service.IRateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OursudokuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateServiceImplTest {

	@Autowired
	IRateService iRateService;

	@Test
	public void refresh() {
		iRateService.Refresh();
	}
}