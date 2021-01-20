package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Reward;
import com.seg02.oursudoku.info.RewardInfo;
import com.seg02.oursudoku.mapper.RewardMapper;
import com.seg02.oursudoku.service.IRewardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements IRewardService {

	@Resource
	RewardMapper rewardMapper;

	@Override
	public List<RewardInfo> findByState(String rewardState, Integer Count) {
		List<Reward> rewardList = rewardMapper.selectByRewardState(rewardState);
		List<RewardInfo> ans = null;
		if (rewardList != null) {
			Collections.shuffle(rewardList);
			ans = new ArrayList<RewardInfo>();
			for (int i = 0; i < Math.min(Count, rewardList.size()); i++) {
				ans.add(rewardList.get(i).change());
			}
		}
		return ans;
	}

	@Override
	public Boolean creatReward(RewardInfo rewardInfo) {
		rewardInfo.setRewardId(String.valueOf(rewardMapper.selectMaxRid() + 1));
		rewardMapper.insertReward(rewardInfo.getRewardId(), rewardInfo.getProblemId(), rewardInfo.getRewardMoney());
		return rewardMapper.selectByRid(rewardInfo.getRewardId()) != null;
	}

	@Override
	public void solve(String rewardID) {
		rewardMapper.updateState(rewardID);
	}
}
