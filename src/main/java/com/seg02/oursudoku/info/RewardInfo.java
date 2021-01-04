package com.seg02.oursudoku.info;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class RewardInfo {
	private String rewardId;

	private String problemId;

	private Integer rewardMoney;

	private String rewardState;
}
