package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.RoomInfo;
import lombok.Data;

import java.util.List;

@Data
public class RoomPageRes {
	private List<RoomInfo> roomInfoList;
}
