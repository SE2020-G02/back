package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.seg02.oursudoku.info.RewardInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reward implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("reward_ID")
	private String rewardId;

	@TableField("problem_ID")
	private String problemId;

	private Integer rewardMoney;

	private String rewardState;

	public RewardInfo change() {
		RewardInfo ans = new RewardInfo();
		MyBeanUtils.copyProperties(this, ans);
		return ans;
	}


}
