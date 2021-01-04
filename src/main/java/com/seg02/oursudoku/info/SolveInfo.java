package com.seg02.oursudoku.info;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class SolveInfo {
	private String problemId;

	private String accountId;

	private Integer solveCostTime;
}
