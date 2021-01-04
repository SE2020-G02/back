package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Solve implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("problem_ID")
	private String problemId;

	@TableField("account_ID")
	private String accountId;

	private Integer solveCostTime;

	public String changeTime() {
		int h, m;
		h = this.solveCostTime / 60;
		m = this.solveCostTime % 60;

		return String.format("%02d'%02d''", h, m);
	}


}
