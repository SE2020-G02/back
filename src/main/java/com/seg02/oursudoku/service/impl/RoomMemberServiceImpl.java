package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.RoomMember;
import com.seg02.oursudoku.info.RoomMemberInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
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


	@Override
	public Boolean roomJoin(Integer roomId, String accountId) {
		roomMemberMapper.join(roomId, accountId);
		return roomMemberMapper.selectByRA(roomId, accountId) != null;
	}

	@Override
	public Boolean roomQuit(Integer roomId, String accountId) {
		if (roomMemberMapper.selectByRA(roomId, accountId) != null) {
			roomMemberMapper.quit(roomId, accountId);
			return roomMemberMapper.selectByRA(roomId, accountId) == null;
		} else {
			return false;
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
	public List<RoomMemberInfo> getMessage(Integer roomId) {
		List<RoomMember> roomMemberList = roomMemberMapper.selectMessage(roomId);
		List<RoomMemberInfo> roomMemberInfoList = new ArrayList<RoomMemberInfo>();
		for (int i = 0; i < roomMemberList.size(); i++) {
			RoomMemberInfo info = new RoomMemberInfo();
			info.setAccountNickname(accountMapper.selectNameById(roomMemberList.get(i).getAccountId()));
			info.setRoomId(roomMemberList.get(i).getRoomId());
			info.setPercentage(roomMemberList.get(i).getPercentage());
			info.setTimecost(roomMemberList.get(i).getTimecost());
			roomMemberInfoList.add(info);
		}
		return roomMemberInfoList;
	}

	@Override
	public List<String> getRoomMessage(Integer roomId) {
		return roomMemberMapper.selectNames(roomId);
	}
}
