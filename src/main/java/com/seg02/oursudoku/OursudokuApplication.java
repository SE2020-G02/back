package com.seg02.oursudoku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.seg02.oursudoku.mapper")
@EnableScheduling
public class OursudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(OursudokuApplication.class, args);
	}

}
