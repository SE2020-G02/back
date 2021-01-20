package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.UserAppeal;
import com.seg02.oursudoku.info.UserAppealInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.mapper.BlogsMapper;
import com.seg02.oursudoku.mapper.UserAppealMapper;
import com.seg02.oursudoku.service.IUserAppealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
@Service
public class UserAppealServiceImpl extends ServiceImpl<UserAppealMapper, UserAppeal> implements IUserAppealService {

	@Resource
	UserAppealMapper userAppealMapper;

	@Resource
	BlogsMapper blogsMapper;

	@Resource
	AccountMapper accountMapper;


	@Override
	public Boolean submit(UserAppeal userAppeal) {
		userAppeal.setAppealId(String.valueOf(userAppealMapper.selectMaxAppealId() + 1));
		return userAppealMapper.insert(userAppeal) == 1;
	}

	@Override
	public List<UserAppealInfo> getAll(String state) {
		List<UserAppeal> userAppealList = userAppealMapper.selectByState(state);

		List<UserAppealInfo> userAppealInfoList = new ArrayList<UserAppealInfo>();
		for (int i = 0; i < userAppealList.size(); i++) {
			userAppealList.get(i).setAccountId(accountMapper.selectNameById(userAppealList.get(i).getAccountId()));
			userAppealInfoList.add(userAppealList.get(i).change());
		}

		return userAppealInfoList;
	}

	@Override
	public Boolean action(String appealId) {
		UserAppeal userAppeal = userAppealMapper.selectById(appealId);
		if (userAppeal.getAppealType().equals("博客")) {
			blogsMapper.deleteRByBid(userAppeal.getAppealTypeId());
			blogsMapper.deleteBByBid(userAppeal.getAppealTypeId());
			userAppealMapper.updateState(appealId, "已解决");
			return blogsMapper.selectByBid(userAppeal.getAppealTypeId()) == null;
		}
		return false;
	}

	@Override
	public Boolean down(String appealId) {
		userAppealMapper.updateState(appealId, "被驳回");
		return true;
	}
}
