package com.seg02.oursudoku.info;


import lombok.Data;

import java.time.LocalTime;

@Data
public class RoomInfo {

	private Integer roomId;

	private String problemId;

	private String accountId;

	private String accountNickname;

	private String roomStartTime;

	private Integer roomRunningTime;

	private Integer roomLevel;

	private String roomState;

	private Integer roomMemberCount;
}
