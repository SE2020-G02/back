package com.seg02.oursudoku.controller;


import com.seg02.oursudoku.req.RoomCreatReq;
import com.seg02.oursudoku.req.RoomPageReq;
import com.seg02.oursudoku.req.RoomStateReq;
import com.seg02.oursudoku.res.RoomCreatRes;
import com.seg02.oursudoku.res.RoomPageRes;
import com.seg02.oursudoku.service.IRoomService;
import com.seg02.oursudoku.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	 * 创建房间
	 *
	 * @param req
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/creat")
	public ResultBean<RoomCreatRes> creatRoom(@RequestBody RoomCreatReq req) throws ParseException {
		ResultBean<RoomCreatRes> res = new ResultBean<RoomCreatRes>();

		RoomCreatRes roomCreatRes = new RoomCreatRes();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date ds = df.parse(req.getRoomStartTime());
		Date dn = new Date(System.currentTimeMillis());

		if (ds.getTime() > dn.getTime()) {
			roomCreatRes.setRoomInfo(iRoomService.creat(req.getAccountId(), req.getRoomStartTime(), req.getRoomRunningTime(), req.getRoomLevel()));
			res.setData(roomCreatRes);
			if (roomCreatRes.getRoomInfo() != null) {
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

	/**
	 * 根据等级获取一页的房间信息
	 *
	 * @param req
	 * @return
	 * @throws ParseException
	 */
	@CrossOrigin
	@PostMapping("/page")
	public ResultBean<RoomPageRes> roomPage(@RequestBody RoomPageReq req) throws ParseException {
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


	/**
	 * 根据状态获取一页的房间信息
	 *
	 * @param req
	 * @return
	 * @throws ParseException
	 */
	@CrossOrigin
	@PostMapping("/state")
	public ResultBean<RoomPageRes> getByState(@RequestBody RoomStateReq req) throws ParseException {
		RoomPageRes roomPageRes = new RoomPageRes();
		roomPageRes.setRoomInfoList(iRoomService.roomState(req.getAccountId(), req.getProblemState()));
		ResultBean<RoomPageRes> res = new ResultBean<RoomPageRes>();
		res.setData(roomPageRes);
		if (res.getData().getRoomInfoList().size() != 0) {
			if (roomPageRes.getRoomInfoList() != null) {
				res.setMsg("查询成功");
				res.setCode(ResultBean.SUCCESS);
			} else {
				res.setMsg("查询失败");
				res.setCode(ResultBean.FAIL);
			}
		} else {
			res.setMsg("暂无房间信息");
			res.setCode(ResultBean.SUCCESS);
		}
		return res;
	}
}
