package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class RoomMemberReq {
	private Integer roomId;

	private String accountId;

	private Integer timecost;

	private Double percentage;
}
