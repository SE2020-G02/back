package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.RoomCreatReq;
import com.seg02.oursudoku.req.RoomPageReq;
import com.seg02.oursudoku.req.RoomStateReq;
import com.seg02.oursudoku.res.RoomCreatRes;
import com.seg02.oursudoku.res.RoomPageRes;
import com.seg02.oursudoku.service.IRoomService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	IRoomService iRoomService;

	/**
	 * 接口文档未添加
	 *
	 * @param req
	 * @return
	 */
	@PostMapping("/creat")
	public ResultBean<RoomCreatRes> creatRoom(@RequestBody RoomCreatReq req) throws ParseException {
		ResultBean<RoomCreatRes> res = new ResultBean<RoomCreatRes>();

		RoomCreatRes roomCreatRes = new RoomCreatRes();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date ds = df.parse(req.getRoomStartTime());
		Date dn = new Date(System.currentTimeMillis());
		if (ds.getTime() > dn.getTime()) {
			roomCreatRes.setRoomInfo(iRoomService.creat(req.getAccountId(), req.getRoomStartTime(), req.getRoomRunningTime(), req.getRoomLevel()));
			res.setData(roomCreatRes);
			if (roomCreatRes != null) {
				res.setMsg("创建成功");
				res.setCode(ResultBean.SUCCESS);
			} else {
				res.setMsg("创建失败");
				res.setCode(ResultBean.FAIL);
			}
		} else {
			res.setMsg("开始时间不符合规定");
			res.setCode(ResultBean.FAIL);
			res.setData(null);
		}

		return res;
	}

	@PostMapping("/page")
	public ResultBean<RoomPageRes> roomPage(@RequestBody RoomPageReq req) {
		ResultBean<RoomPageRes> res = new ResultBean<RoomPageRes>();
		RoomPageRes roomPageRes = new RoomPageRes();
		roomPageRes.setRoomInfoList(iRoomService.pageLevel(req.getRoomLevel(), req.getAccountId()));
		res.setData(roomPageRes);
		if (res.getData() != null) {
			res.setMsg("查询成功");
			res.setCode(ResultBean.SUCCESS);
		} else {
			res.setMsg("查询失败");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}

	@PostMapping("/state")
	public ResultBean<RoomCreatRes> getByState(@RequestBody RoomStateReq req) {
		RoomCreatRes roomCreatRes = new RoomCreatRes();
		roomCreatRes.setRoomInfo(iRoomService.roomState(req.getAccountId(), req.getProblemState()));
		ResultBean<RoomCreatRes> res = new ResultBean<RoomCreatRes>();
		res.setData(roomCreatRes);
		if (res.getData() != null) {
			if (roomCreatRes != null) {
				res.setMsg("查询成功");
				res.setCode(ResultBean.SUCCESS);
			} else {
				res.setMsg("查询失败");
				res.setCode(ResultBean.FAIL);
			}
		} else {
			res.setMsg("暂无房间信息");
			res.setCode(ResultBean.FAIL);
		}
		return res;
	}


}
