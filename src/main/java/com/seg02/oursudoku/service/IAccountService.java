package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Account;
import com.seg02.oursudoku.entity.AccountPublic;
import com.seg02.oursudoku.info.AccountInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
public interface IAccountService {

	/**
	 * 根据用户昵称查找用户
	 *
	 * @param accountNickname
	 * @return
	 */
	public AccountInfo findByNickname(String accountNickname);


	/**
	 * 插入用户
	 *
	 * @param accountInfo
	 * @return
	 */
	public boolean insertAccount(AccountInfo accountInfo);


	/**
	 * 更新用户密码
	 *
	 * @param accountInfo
	 * @return
	 */
	public boolean updateAccountPwd(AccountInfo accountInfo);

	/**
	 * 获取新用户id
	 *
	 * @return
	 */
	public String findAID();


	/**
	 * 查询电话是否存在
	 *
	 * @param accountPhone
	 * @return
	 */
	public boolean findPhone(String accountPhone);


	/**
	 * 根据用户id查询用户名
	 *
	 * @param accountId
	 * @return
	 */
	public String findNameById(String accountId);

	public AccountPublic findPublicMessage(String accountId);
}
