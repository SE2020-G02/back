package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Rate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
public interface RateMapper extends BaseMapper<Rate> {

	List<Rate> selectByCount(@Param("ratePage") Integer ratePage, @Param("rateCount") Integer rateCount);

	int selectRateCount();

	int getGameCount();

	int getGamePeople();

}