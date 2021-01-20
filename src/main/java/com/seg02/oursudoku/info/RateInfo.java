package com.seg02.oursudoku.info;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class RateInfo {
	private Integer rateId;

	private String accountId;

	private String accountNickname;

	private Integer rateGameCount;

	private Integer rateAvgTime;

	private Integer rateNumber;

	private String rateState;

	private Integer accountLevel;

}
