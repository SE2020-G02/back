package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
public interface RoomMapper extends BaseMapper<Room> {

	/**
	 * 创建房间
	 *
	 * @param roomID
	 * @param problemID
	 * @param accountID
	 * @param RST
	 * @param RRT
	 * @param level
	 */
	public void creatRoom(@Param("Rid") Integer roomID, @Param("Pid") String problemID, @Param("Aid") String accountID, @Param("RST") String RST, @Param("RRT") Integer RRT, @Param("level") Integer level, @Param("state") String state);


	/**
	 * 查询最大房间号
	 *
	 * @return
	 */
	public int selectMaxRid();


	/**
	 * 查询房间等级的所有房间
	 *
	 * @param level
	 * @return
	 */
	public List<Room> selectRoomByLevel(@Param("level") Integer level, @Param("Aid") String Aid);

	public List<Room> selectByState(@Param("roomState") String roomState, @Param("Aid") String Aid);

	public void changeState(@Param("Rid") Integer Rid, @Param("cState") String cState);

	public Room selectByRid(@Param("Rid") Integer Rid);

	public void deleteRid(@Param("Rid") Integer Rid);
}
