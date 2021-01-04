package com.seg02.oursudoku.entity;

import com.seg02.oursudoku.info.AccountPublicInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountPublic implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountProfilePhoto;

	private String accountNickname;

	private String accountSignature;

	private Integer accountLevel;

	public AccountPublicInfo change() {
		AccountPublicInfo accountPublicInfo = new AccountPublicInfo();
		MyBeanUtils.copyProperties(this, accountPublicInfo);
		return accountPublicInfo;
	}
}
