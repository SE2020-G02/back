package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Room;
import com.seg02.oursudoku.entity.RoomMember;
import com.seg02.oursudoku.info.RoomMemberInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.mapper.RewardMapper;
import com.seg02.oursudoku.mapper.RoomMapper;
import com.seg02.oursudoku.mapper.RoomMemberMapper;
import com.seg02.oursudoku.service.IRoomMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@Service
public class RoomMemberServiceImpl implements IRoomMemberService {

	@Resource
	RoomMemberMapper roomMemberMapper;

	@Resource
	AccountMapper accountMapper;

	@Resource
	RoomMapper roomMapper;


	@Override
	public Boolean roomJoin(Integer roomId, String accountId) {
		roomMemberMapper.join(roomId, accountId);
		return roomMemberMapper.selectByRA(roomId, accountId) != null;
	}

	@Override
	public Boolean roomQuit(Integer roomId, String accountId) {
		if (!(roomMapper.selectByRid(roomId).getAccountId().equals(accountId))) {
			if (roomMemberMapper.selectByRA(roomId, accountId) != null) {
				roomMemberMapper.quit(roomId, accountId);
				return roomMemberMapper.selectByRA(roomId, accountId) == null;
			} else {
				return false;
			}
		} else {
			roomMemberMapper.deleteRid(roomId);
			roomMapper.deleteRid(roomId);
			return true;
		}

	}

	@Override
	public Boolean putMessage(Integer roomId, String accountId, Integer timecost, Double percentage) {
		if (timecost == -1 || percentage == -1) {
			return true;
		} else {
			roomMemberMapper.updateMessage(timecost, percentage, roomId, accountId);
			return roomMemberMapper.selectByRA(roomId, accountId) != null;
		}
	}

	@Override
	public Boolean getMessage(Integer roomId, String accountId) {
		RoomMember roomMember = roomMemberMapper.selectByRA(roomId, accountId);
		return roomMember.getPercentage() != 100;
	}


	@Override
	public List<RoomMemberInfo> getMessages(Integer roomId) {
		List<RoomMember> roomMemberList = roomMemberMapper.selectMessage(roomId);
		List<RoomMemberInfo> roomMemberInfoList = new ArrayList<RoomMemberInfo>();
		for (int i = 0; i < roomMemberList.size(); i++) {
			RoomMemberInfo info = new RoomMemberInfo();
			info.setAccountNickname(accountMapper.selectNameById(roomMemberList.get(i).getAccountId()));
			info.setRoomId(roomMemberList.get(i).getRoomId());
			info.setPercentage(roomMemberList.get(i).getPercentage());
			info.setTimecost(roomMemberList.get(i).getTimecost());
			info.setRankNum(i + 1);
			roomMemberInfoList.add(info);
		}
		return roomMemberInfoList;
	}

	@Override
	public List<String> getRoomMessage(Integer roomId) {
		return roomMemberMapper.selectNames(roomId);
	}


}
