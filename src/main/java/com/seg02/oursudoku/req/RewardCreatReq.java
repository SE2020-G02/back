package com.seg02.oursudoku.req;

import com.seg02.oursudoku.info.ProblemInfo;
import com.seg02.oursudoku.info.RewardInfo;
import lombok.Data;

@Data
public class RewardCreatReq {
	private RewardInfo rewardInfo;

	private ProblemInfo problemInfo;
}
