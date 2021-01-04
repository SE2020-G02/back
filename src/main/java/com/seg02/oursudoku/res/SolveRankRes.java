package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.SolveRankInfo;
import lombok.Data;

import java.util.List;

@Data
public class SolveRankRes {
	private Integer solveCount;

	private List<SolveRankInfo> solveRankInfoList;

}
