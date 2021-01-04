package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class ResetPwdReq {
	private String accountNickname;
	private String accountPhone;
}
