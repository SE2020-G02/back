package com.seg02.oursudoku.info;

import lombok.Data;

@Data
public class UserAppealInfo {
	private String appealId;

	private String accountId;

	private String appealState;

	private String appealMessage;

	private String appealType;

	private String appealTypeId;
}
