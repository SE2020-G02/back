package com.seg02.oursudoku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.seg02.oursudoku.entity.Account;
import com.seg02.oursudoku.entity.AccountPublic;
import com.seg02.oursudoku.info.AccountInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
@Service
public class AccountServiceImpl implements IAccountService {

	@Resource
	AccountMapper accountMapper;

	@Override
	public AccountInfo findByNickname(String accountNickname) {
		LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<Account>();
		wrapper.eq(Account::getAccountNickname, accountNickname);

		List<Account> list = accountMapper.selectList(wrapper);
		if (list.size() > 0) {
			return new AccountInfo(list.get(0));
		} else {
			return null;
		}
	}

	@Override
	public boolean insertAccount(AccountInfo accountInfo) {
		return accountMapper.insert(new Account(accountInfo)) == 1;
	}

	@Override
	public boolean updateAccountPwd(AccountInfo accountInfo) {
		Account account = new Account(accountInfo);

		LambdaUpdateWrapper<Account> wrapper = new LambdaUpdateWrapper<Account>();
		wrapper.eq(Account::getAccountId, account.getAccountId()).set(Account::getAccountPwd, account.getAccountPwd());
		int line = accountMapper.update(null, wrapper);

		return line == 1;
	}

	@Override
	public String findAID() {
		return String.valueOf(Integer.parseInt(accountMapper.selectMaxId()) + 1);
	}

	@Override
	public boolean findPhone(String accountPhone) {
		return accountMapper.selectByPhone(accountPhone) == null;
	}

	@Override
	public String findNameById(String accountId) {
		return accountMapper.selectNameById(accountId);
	}

	@Override
	public AccountPublic findPublicMessage(String accountId) {
		return accountMapper.selectPublicMessage(accountId);
	}

}
