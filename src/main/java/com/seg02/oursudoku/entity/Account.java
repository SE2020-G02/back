package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.seg02.oursudoku.info.AccountInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("account_ID")
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

	public Account() {

	}

	public Account(AccountInfo accountInfo) {
		MyBeanUtils.copyProperties(accountInfo, this);
	}

	public AccountInfo change() {
		AccountInfo accountInfo = new AccountInfo();
		MyBeanUtils.copyProperties(this, accountInfo);
		return accountInfo;
	}

}
