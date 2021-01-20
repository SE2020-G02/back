package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.AdministratorInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
public interface IAdministratorService extends IService<Administrator> {

	public Boolean login(String id, String pwd);
}
