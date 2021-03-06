package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.seg02.oursudoku.info.RateInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer rateId;

	@TableField("account_ID")
	private String accountId;

	private Integer rateGameCount;

	private Integer rateAvgTime;

	private Integer rateNumber;

	private String rateState;

	public RateInfo change() {
		RateInfo ans = new RateInfo();
		MyBeanUtils.copyProperties(this, ans);
		return ans;
	}

	public void addGameCount() {
		this.rateGameCount += 1;
	}

	public void addAvgTime(Integer num) {
		this.rateAvgTime += num;
	}

	public int compareTo(Rate rate) {
		if (!rate.getRateNumber().equals(this.getRateNumber())) {
			return rate.getRateNumber() - this.getRateNumber();
		} else if (!rate.getRateAvgTime().equals(this.getRateAvgTime())) {
			return rate.getRateAvgTime() - this.getRateAvgTime();
		} else if (!rate.getRateGameCount().equals(this.getRateGameCount())) {
			return rate.getRateGameCount() - this.getRateGameCount();
		} else {
			return 0;
		}
	}
}
