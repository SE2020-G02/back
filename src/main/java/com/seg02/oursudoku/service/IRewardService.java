package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Reward;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.RewardInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
public interface IRewardService extends IService<Reward> {

	public List<RewardInfo> findByState(String rewardState, Integer Count);

	public Boolean creatReward(RewardInfo rewardInfo);

}
