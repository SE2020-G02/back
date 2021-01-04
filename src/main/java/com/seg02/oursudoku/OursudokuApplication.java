package com.seg02.oursudoku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seg02.oursudoku.mapper")
public class OursudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(OursudokuApplication.class, args);
	}

}
