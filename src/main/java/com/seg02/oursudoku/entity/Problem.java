package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.seg02.oursudoku.info.ProblemInfo;
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
public class Problem implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("problem_ID")
	private String problemId;

	private Integer problemLevel;

	private String problemPanes;

	private String problemInfo;

	public ProblemInfo change() {
		ProblemInfo problemInfo = new ProblemInfo();
		MyBeanUtils.copyProperties(this, problemInfo);
		return problemInfo;
	}

}
