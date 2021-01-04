package com.seg02.oursudoku.res;

import lombok.Data;

import java.util.List;

@Data
public class RoomMemberMessageRes {

	private Integer memberCount;

	private List<String> accountsName;
}
