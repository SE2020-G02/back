package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.seg02.oursudoku.info.UserAppealInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAppeal implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("appeal_ID")
	private String appealId;

	@TableField("account_ID")
	private String accountId;

	private String appealState;

	private String appealMessage;

	private String appealType;

	@TableField("appeal_type_ID")
	private String appealTypeId;

	public UserAppealInfo change() {
		UserAppealInfo userAppealInfo = new UserAppealInfo();
		MyBeanUtils.copyProperties(this, userAppealInfo);
		return userAppealInfo;
	}


}
