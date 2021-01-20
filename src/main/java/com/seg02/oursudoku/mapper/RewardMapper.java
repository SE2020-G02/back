package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Reward;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
public interface RewardMapper extends BaseMapper<Reward> {

	public List<Reward> selectByRewardState(@Param("state") String state);

	public void insertReward(@Param("Rid") String Rid, @Param("Pid") String Pid, @Param("Rmoney") Integer Rmoney);

	public int selectMaxRid();

	public Reward selectByRid(@Param("Rid") String Rid);

	public void updateState(@Param("Rid") String Rid);

}
