package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.RoomIdReq;
import com.seg02.oursudoku.req.RoomJoinReq;
import com.seg02.oursudoku.req.RoomMemberReq;
import com.seg02.oursudoku.res.RoomGameMessageRes;
import com.seg02.oursudoku.res.RoomMemberMessageRes;
import com.seg02.oursudoku.service.IRoomMemberService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@RestController
@RequestMapping("/roomMember")
public class RoomMemberController {

	@Autowired
	IRoomMemberService iRoomMemberService;

	@PostMapping("/join")
	public ResultBean<Boolean> roomJoin(@RequestBody RoomJoinReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		res.setData(iRoomMemberService.roomJoin(req.getRoomId(), req.getAccountID()));
		if (res.getData()) {
			res.setMsg("加入成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("加入失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/quit")
	public ResultBean<Boolean> roomQuit(@RequestBody RoomJoinReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();
		res.setData(iRoomMemberService.roomQuit(req.getRoomId(), req.getAccountID()));
		if (res.getData()) {
			res.setMsg("退出成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("退出失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/getMessage")
	public ResultBean<RoomGameMessageRes> getMessage(@RequestBody RoomMemberReq req) {
		ResultBean<RoomGameMessageRes> res = new ResultBean<RoomGameMessageRes>();
		if (iRoomMemberService.putMessage(req.getRoomId(), req.getAccountId(), req.getTimecost(), req.getPercentage())) {
			RoomGameMessageRes roomGameMessageRes = new RoomGameMessageRes();
			roomGameMessageRes.setRoomMemberInfoList(iRoomMemberService.getMessage(req.getRoomId()));
			roomGameMessageRes.setRoomAccountCount(roomGameMessageRes.getRoomMemberInfoList().size());
			res.setData(roomGameMessageRes);
			res.setMsg("获取成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setData(null);
			res.setMsg("获取失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/room/message")
	public ResultBean<RoomMemberMessageRes> roomMessage(@RequestBody RoomIdReq req) {
		ResultBean<RoomMemberMessageRes> res = new ResultBean<RoomMemberMessageRes>();

		RoomMemberMessageRes roomMemberMessageRes = new RoomMemberMessageRes();
		roomMemberMessageRes.setAccountsName(iRoomMemberService.getRoomMessage(req.getRoomId()));
		if (roomMemberMessageRes.getAccountsName() != null) {
			roomMemberMessageRes.setMemberCount(roomMemberMessageRes.getAccountsName().size());
			res.setData(roomMemberMessageRes);
			res.setMsg("获取成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setCode(ResultBean.FAIL);
			res.setMsg("获取失败");
			res.setData(null);
		}
		return res;
	}
}
