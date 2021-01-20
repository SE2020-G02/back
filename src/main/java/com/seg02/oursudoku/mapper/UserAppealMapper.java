package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.UserAppeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2021-01-11
 */
public interface UserAppealMapper extends BaseMapper<UserAppeal> {

	public Integer selectMaxAppealId();

	public List<UserAppeal> selectByState(@Param("state") String state);

	public void updateState(@Param("Aid") String Aid, @Param("state") String state);

}
