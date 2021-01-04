package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.RoomMemberInfo;
import lombok.Data;

import java.util.List;

@Data
public class RoomGameMessageRes {

	private Integer roomAccountCount;

	private List<RoomMemberInfo> roomMemberInfoList;
}
