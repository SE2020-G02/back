package com.seg02.oursudoku.req;

import lombok.Data;

@Data
public class RoomStateReq {
	private String accountId;

	private String problemState;
}
