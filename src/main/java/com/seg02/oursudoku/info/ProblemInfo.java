package com.seg02.oursudoku.info;

import com.seg02.oursudoku.entity.Problem;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;

@Data
public class ProblemInfo {
	private String problemId;

	private Integer problemLevel;

	private String problemPanes;

	private String problemInfo;

	private Integer problemSolveCount;

	public Problem change() {
		Problem problem = new Problem();
		MyBeanUtils.copyProperties(this, problem);
		return problem;
	}

	public Integer[][] getPanes() {
		Integer[][] ans = new Integer[9][9];
		for (int i = 0; i < this.getProblemPanes().length(); i++) {
			ans[i / 9][i % 9] = this.getProblemPanes().charAt(i) - '0';
		}
		return ans;
	}

	public static void main(String[] args) {
//		ProblemInfo info = new ProblemInfo();
//		info.setProblemPanes("000006010004012007519070026140069750750000089092150034930080245400720900020900000");
//		String[][] ans = info.getPanes();
//		System.out.println(ans[0].toString());
	}
}
