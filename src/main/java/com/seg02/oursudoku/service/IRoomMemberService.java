package com.seg02.oursudoku.service;

import com.seg02.oursudoku.info.RoomMemberInfo;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
public interface IRoomMemberService {

	/**
	 * 用户加入房间，返回是否成功
	 *
	 * @param roomId
	 * @param accountId
	 * @return
	 */
	public Boolean roomJoin(Integer roomId, String accountId);

	/**
	 * 用户退出房间，返回是否成功
	 *
	 * @param roomId
	 * @param accountId
	 * @return
	 */
	public Boolean roomQuit(Integer roomId, String accountId);

	/**
	 * 上传用户比赛实时信息，如果比赛信息中存在-1，则代表未在比赛时获取信息，直接返回true
	 *
	 * @param roomId
	 * @param accountId
	 * @param timecost
	 * @param percentage
	 * @return
	 */
	public Boolean putMessage(Integer roomId, String accountId, Integer timecost, Double percentage);

	public Boolean getMessage(Integer roomId, String accountId);


	/**
	 * 返回房间内比赛所有选手的进度信息
	 *
	 * @param roomId
	 * @return
	 */
	public List<RoomMemberInfo> getMessages(Integer roomId);

	public List<String> getRoomMessage(Integer roomId);

}
