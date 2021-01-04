package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seg02.oursudoku.entity.AccountPublic;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
public interface AccountMapper extends BaseMapper<Account> {

	public String selectMaxId();

	public Account selectByPhone(@Param("phone") String phone);

	public String selectNameById(@Param("id") String id);

	public AccountPublic selectPublicMessage(@Param("Aid") String Aid);


}
