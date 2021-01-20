package com.seg02.oursudoku.util;

public class ProblemUtil {

	/**
	 * 判断题目是否可解，待写
	 *
	 * @param panes
	 * @return
	 */
	public static Boolean judge(String panes) {
//		int x = 0;
//		for (int i = 0; i < panes.length(); i++) {
//			if (panes.charAt(i) != '0') {
//				x++;
//			}
//		}
//		return x >= 17;
		return Sudoku.ss(panes);
	}

	public static void main(String[] args) {
		System.out.println(judge("900003070006000009507900000010098000040060020000230010000006703100000500090700004"));
	}
}
