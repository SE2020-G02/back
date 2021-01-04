package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class RateLimitReq {
	private Integer rateCount;

	private Integer ratePage;
}
