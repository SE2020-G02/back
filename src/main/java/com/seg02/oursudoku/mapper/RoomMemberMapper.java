package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.RoomMember;
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
public interface RoomMemberMapper extends BaseMapper<RoomMember> {

	public void join(@Param("Rid") Integer Rid, @Param("Aid") String Aid);

	public void quit(@Param("Rid") Integer Rid, @Param("Aid") String Aid);

	public RoomMember selectByRA(@Param("Rid") Integer Rid, @Param("AId") String Aid);

	public void updateMessage(@Param("timecost") Integer timecost, @Param("percentage") Double percentage, @Param("Rid") Integer Rid, @Param("Aid") String Aid);

	public List<RoomMember> selectMessage(@Param("Rid") Integer Rid);

	public List<String> selectNames(@Param("Rid") Integer Rid);
}
