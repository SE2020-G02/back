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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		roomInfo.setProblemId(list.get((int) (Math.random() * list.size())));
		roomInfo.setRoomStartTime(roomStartTime);
		roomInfo.setRoomRunningTime(roomRunningTime);
		roomInfo.setRoomLevel(roomLevel);
		roomInfo.setRoomState("待开始");

		roomMapper.creatRoom(roomInfo.getRoomId(), roomInfo.getProblemId(), roomInfo.getAccountId(), roomInfo.getRoomStartTime(), roomInfo.getRoomRunningTime(), roomInfo.getRoomLevel(), roomInfo.getRoomState());

		roomMemberMapper.join(roomInfo.getRoomId(), roomInfo.getAccountId());

		return roomInfo;
	}

	@Override
	public List<RoomInfo> pageLevel(Integer roomLevel, String accountId)  throws ParseException {
		List<RoomInfo> ans = new ArrayList<RoomInfo>();
		List<Room> roomList = roomMapper.selectRoomByLevel(roomLevel, accountId);
		for (int i = 0; i < roomList.size(); i++) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date db = df.parse(roomList.get(i).getRoomStartTime());
			Date dn = new Date(System.currentTimeMillis());
			long diffTime = dn.getTime() - db.getTime();
			diffTime /= 1000;

			if (diffTime >= 0 && diffTime <= 60 * roomList.get(i).getRoomRunningTime()) {
				roomMapper.changeState(roomList.get(i).getRoomId(), "比赛中");
				roomList.get(i).setRoomState("比赛中");
			} else if (diffTime > 60 * roomList.get(i).getRoomRunningTime()) {
				roomMapper.changeState(roomList.get(i).getRoomId(), "已结束");
				roomList.get(i).setRoomState("已结束");
			}

			RoomInfo roomInfo = roomList.get(i).change();
			roomInfo.setAccountNickname(accountMapper.selectNameById(roomInfo.getAccountId()));
			roomInfo.setRoomMemberCount(roomMemberMapper.selectMessage(roomInfo.getRoomId()).size());
			ans.add(roomInfo);
		}
		return ans;
	}

	@Override
	public List<RoomInfo> roomState(String accountId, String roomState) throws ParseException {
		List<RoomInfo> ans = new ArrayList<RoomInfo>();

		List<Room> roomList = roomMapper.selectByState(roomState, accountId);

		for (int i = 0; i < roomList.size(); i++) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date db = df.parse(roomList.get(i).getRoomStartTime());
			Date dn = new Date(System.currentTimeMillis());
			long diffTime = dn.getTime() - db.getTime();
			diffTime /= 1000;

			if (diffTime >= 0 && diffTime <= 60 * roomList.get(i).getRoomRunningTime()) {
				roomMapper.changeState(roomList.get(i).getRoomId(), "比赛中");
				roomList.get(i).setRoomState("比赛中");
			} else if (diffTime > 60 * roomList.get(i).getRoomRunningTime()) {
				roomMapper.changeState(roomList.get(i).getRoomId(), "已结束");
				roomList.get(i).setRoomState("已结束");
			}
			if (roomList.get(i).getRoomState().equals(roomState)) {
				RoomInfo roomInfo = roomList.get(i).change();
				roomInfo.setAccountNickname(accountMapper.selectNameById(roomInfo.getAccountId()));
				roomInfo.setRoomMemberCount(roomMemberMapper.selectMessage(roomInfo.getRoomId()).size());
				ans.add(roomInfo);
			}
		}
		return ans;

	}
}
