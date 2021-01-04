package com.seg02.oursudoku.info;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class RoomMemberInfo {
	private Integer roomId;

	private String accountNickname;

	private Integer timecost;

	private Double percentage;
}
