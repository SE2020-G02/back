package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.RoomIdReq;
import com.seg02.oursudoku.req.RoomJoinReq;
import com.seg02.oursudoku.req.RoomMemberReq;
import com.seg02.oursudoku.req.RoomSubmitReq;
import com.seg02.oursudoku.res.RoomGameMessageRes;
import com.seg02.oursudoku.res.RoomMemberMessageRes;
import com.seg02.oursudoku.service.IRoomMemberService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@CrossOrigin
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

	@CrossOrigin
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

	@CrossOrigin
	@PostMapping("/getMessage")
	public ResultBean<RoomGameMessageRes> getMessage(@RequestBody RoomMemberReq req) {
		ResultBean<RoomGameMessageRes> res = new ResultBean<RoomGameMessageRes>();

		RoomGameMessageRes roomGameMessageRes = new RoomGameMessageRes();
		roomGameMessageRes.setRoomMemberInfoList(iRoomMemberService.getMessages(req.getRoomId()));
		roomGameMessageRes.setRoomAccountCount(roomGameMessageRes.getRoomMemberInfoList().size());
		res.setData(roomGameMessageRes);
		if (res.getData() != null) {
			res.setMsg("获取成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("获取失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@CrossOrigin
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

	@CrossOrigin
	@PostMapping("/submit")
	public ResultBean<Boolean> roomSubmit(@RequestBody RoomSubmitReq req) {
		ResultBean<Boolean> res = new ResultBean<Boolean>();

		if (req.cmp()) {
			if (iRoomMemberService.getMessage(req.getRoomId(), req.getAccountID())) {
				res.setData(iRoomMemberService.putMessage(req.getRoomId(), req.getAccountID(), req.getSolveCostTime(), 100.0));
				if (res.getData()) {
					res.setCode(ResultBean.SUCCESS);
					res.setMsg("答案正确");
				} else {
					res.setCode(ResultBean.FAIL);
					res.setMsg("提交失败");
				}
			} else {
				res.setCode(ResultBean.FAIL);
				res.setMsg("重复提交");
			}
		} else {
			res.setData(false);
			res.setCode(ResultBean.FAIL);
			res.setMsg("答案错误");
		}

		return res;
	}


}
