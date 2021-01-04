package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.seg02.oursudoku.info.RoomInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("room_ID")
	private Integer roomId;

	@TableField("problem_ID")
	private String problemId;

	@TableField("account_ID")
	private String accountId;

	private String roomStartTime;

	private Integer roomRunningTime;

	private Integer roomLevel;

	private String roomState;

	public RoomInfo change() {
		RoomInfo ans = new RoomInfo();
		MyBeanUtils.copyProperties(this, ans);
		return ans;
	}
}
