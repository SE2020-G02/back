package com.seg02.oursudoku.req;

import lombok.Data;

import java.time.LocalTime;

@Data
public class RoomCreatReq {
	private String accountId;

	private String roomStartTime;

	private Integer roomRunningTime;

	private Integer roomLevel;
}
