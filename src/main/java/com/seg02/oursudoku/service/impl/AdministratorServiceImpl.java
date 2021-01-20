package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Administrator;
import com.seg02.oursudoku.mapper.AdministratorMapper;
import com.seg02.oursudoku.service.IAdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seg02.oursudoku.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements IAdministratorService {

	@Resource
	AdministratorMapper administratorMapper;


	@Override
	public Boolean login(String id, String pwd) {
		Administrator administrator = administratorMapper.selectById(id);
		return administrator.getAdminPwd().equals(MD5Util.md5(pwd));
	}
}
