package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Room;
import com.seg02.oursudoku.info.RoomInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.mapper.ProblemMapper;
import com.seg02.oursudoku.mapper.RoomMapper;
import com.seg02.oursudoku.mapper.RoomMemberMapper;
import com.seg02.oursudoku.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

	@Resource
	RoomMapper roomMapper;

	@Resource
	ProblemMapper problemMapper;

	@Resource
	AccountMapper accountMapper;

	@Resource
	RoomMemberMapper roomMemberMapper;

	@Override
	public RoomInfo creat(String accountId, String roomStartTime, Integer roomRunningTime, Integer roomLevel) {
		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setRoomId(roomMapper.selectMaxRid() + 1);
		roomInfo.setAccountId(accountId);
		List<String> list = problemMapper.selectProblemByLevel(roomLevel);
		roomInfo.setProblemId(list.get((int) Math.random() % list.size()));
		roomInfo.setRoomStartTime(roomStartTime);
		roomInfo.setRoomRunningTime(roomRunningTime);
		roomInfo.setRoomLevel(roomLevel);
		roomInfo.setRoomState("未开始");

		roomMapper.creatRoom(roomInfo.getRoomId(), roomInfo.getProblemId(), roomInfo.getAccountId(), roomInfo.getRoomStartTime(), roomInfo.getRoomRunningTime(), roomInfo.getRoomLevel(), roomInfo.getRoomState());

		roomMemberMapper.join(roomInfo.getRoomId(), roomInfo.getAccountId());

		return roomInfo;
	}
	@Override
	public List<RoomInfo> pageLevel(Integer roomLevel, String accountId) {
		List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();
		List<Room> roomList = roomMapper.selectRoomByLevel(roomLevel, accountId);
		for (int i = 0; i < roomList.size(); i++) {
			roomInfoList.add(roomList.get(i).change());
			roomInfoList.get(i).setAccountNickname(accountMapper.selectNameById(roomInfoList.get(i).getAccountId()));
			roomInfoList.get(i).setRoomMemberCount(roomMemberMapper.selectMessage(roomInfoList.get(i).getRoomId()).size());
		}
		return roomInfoList;
	}

	@Override
	public RoomInfo roomState(String accountId, String roomState) {
		Room room = roomMapper.selectByState(accountId, roomState);
		if (room != null) {
			return room.change();
		} else {
			return null;
		}
	}
}
