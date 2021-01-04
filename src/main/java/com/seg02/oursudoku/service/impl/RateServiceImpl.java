package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Rate;
import com.seg02.oursudoku.info.RateInfo;
import com.seg02.oursudoku.mapper.RateMapper;
import com.seg02.oursudoku.res.RateStaticRes;
import com.seg02.oursudoku.service.IRateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@Service
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements IRateService {

	@Resource
	RateMapper rateMapper;

	@Override
	public List<RateInfo> getByCount(Integer ratePage, Integer rateCount) {
		List<Rate> rateList = rateMapper.selectByCount(ratePage, rateCount);
		List<RateInfo> ans = new ArrayList<RateInfo>();
		for (int i = 0; i < rateList.size(); i++) {
			ans.add(rateList.get(i).change());
		}
		return ans;
	}

	@Override
	public Integer getPageCount(Integer rateCount) {
		return rateMapper.selectRateCount() / rateCount + 1;
	}

	@Override
	public RateStaticRes getStaticMessage() {
		RateStaticRes rateStaticRes = new RateStaticRes();
		rateStaticRes.setGameCount(rateMapper.getGameCount());
		rateStaticRes.setGamePeople(rateMapper.getGamePeople());
		return rateStaticRes;
	}
}
