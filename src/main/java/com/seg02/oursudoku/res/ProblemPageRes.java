package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.ProblemInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProblemPageRes {
	private List<ProblemInfo> problemInfoList;
}
