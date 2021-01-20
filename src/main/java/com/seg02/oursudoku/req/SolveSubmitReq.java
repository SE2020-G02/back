package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class SolveSubmitReq {
	private String problemId;

	private String accountId;

	private Integer[][] problemPanes;

	private Integer solveCostTime;

//	public Integer getTime() {
//		String[] ss = solveCostTime.split("'");
//
//		int h, m;
//		h = Integer.parseInt(ss[0]);
//		m = Integer.parseInt(ss[1]);
//
//		return h * 60 + m;
//	}

	public Boolean cmp() {
		Integer[][] s = new Integer[9][9];

//		String str = this.getProblemPanes();
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				s[i][j] = str.charAt(i * 9 + j) - '0';
//			}
//		}
		s = problemPanes;


		int[][] c = new int[18][15];
		for (int i = 0; i < 18; i++) {
			for (int j = 1; j <= 9; j++) {
				c[i][j] = 0;
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				c[i][s[i][j]]++;
			}
		}
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				c[j + 9][s[i][j]]++;
			}
		}
		Boolean ans = true;
		for (int i = 0; i < 18; i++) {
			for (int j = 1; j <= 9; j++) {
				if (c[i][j] != 1) {
					ans = false;
				}
			}
		}
		return ans;
	}
}
