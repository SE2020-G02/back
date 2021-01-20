package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.UserAppeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.UserAppealInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
public interface IUserAppealService extends IService<UserAppeal> {

	public Boolean submit(UserAppeal userAppeal);

	public List<UserAppealInfo> getAll(String state);

	public Boolean action(String appealId);

	public Boolean down(String appealId);
}
