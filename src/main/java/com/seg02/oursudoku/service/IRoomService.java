package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.RoomInfo;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
public interface IRoomService extends IService<Room> {

	/**
	 * 创建房间
	 *
	 * @param accountId
	 * @param roomStartTime
	 * @param roomRunningTime
	 * @param roomLevel
	 * @return
	 */
	public RoomInfo creat(String accountId, String roomStartTime, Integer roomRunningTime, Integer roomLevel);

	/**
	 * 返回房间等级的所有房间
	 *
	 * @param roomLevel
	 * @return
	 */
	public List<RoomInfo> pageLevel(Integer roomLevel, String accountId) throws ParseException;

	public List<RoomInfo> roomState(String accountId, String roomState) throws ParseException;
}
