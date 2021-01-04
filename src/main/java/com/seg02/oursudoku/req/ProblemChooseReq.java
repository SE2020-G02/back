package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class ProblemChooseReq {
	private String accountId;
	private Integer problemLevel;
	private Integer problemCount;
}
