package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class AccountRegisterReq {
	private String accountNickname;
	private String accountPhone;
	private String accountPwd;
}
