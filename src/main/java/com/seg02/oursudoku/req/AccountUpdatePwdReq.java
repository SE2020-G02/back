package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class AccountUpdatePwdReq {
	private String accountNickname;

	private String accountOldPwd;

	private String accountNewPwd;
}
