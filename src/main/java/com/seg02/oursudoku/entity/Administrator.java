package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.seg02.oursudoku.info.AdministratorInfo;
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
public class Administrator implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("admin_ID")
	private String adminId;

	private String adminPwd;

	public AdministratorInfo change() {
		AdministratorInfo administratorInfo = new AdministratorInfo();
		MyBeanUtils.copyProperties(this, administratorInfo);
		return administratorInfo;
	}


}
