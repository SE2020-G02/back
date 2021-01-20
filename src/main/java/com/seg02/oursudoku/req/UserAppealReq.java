package com.seg02.oursudoku.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserAppealReq {
	private String accountId;

	private String appealMessage;

	private String appealType;

	private String appealTypeId;
}
