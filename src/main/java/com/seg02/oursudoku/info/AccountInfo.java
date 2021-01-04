package com.seg02.oursudoku.info;

import com.seg02.oursudoku.entity.Account;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;

@Data
public class AccountInfo {
	private String accountId;

	private String accountPwd;

	private String accountProfilePhoto;

	private String accountNickname;

	private String accountSignature;

	private String accountPhone;

	private Integer accountAge;

	private String accountSex;

	private String accountEducation;

	private Integer accountLevel;


	public AccountInfo() {
	}

	public AccountInfo(Account account) {
		MyBeanUtils.copyProperties(account, this);
	}
}
