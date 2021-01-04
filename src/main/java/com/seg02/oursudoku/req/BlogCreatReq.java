package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class BlogCreatReq {
	private String accountId;

	private String problemId;

	private String blogMessage;
}
