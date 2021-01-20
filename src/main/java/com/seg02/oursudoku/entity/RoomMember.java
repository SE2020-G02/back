package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.seg02.oursudoku.info.RoomMemberInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoomMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("room_ID")
	private Integer roomId;

	@TableField("account_ID")
	private String accountId;

	private Integer timecost;

	private Double percentage;

	public RoomMemberInfo change() {
		RoomMemberInfo roomMemberInfo = new RoomMemberInfo();
		MyBeanUtils.copyProperties(this, roomMemberInfo);
		return roomMemberInfo;
	}
}
