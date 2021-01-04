package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Solve;
import com.seg02.oursudoku.info.SolveInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
public interface ISolveService {


	/**
	 * 根据题面id获取对应的通过排名
	 *
	 * @param problemId
	 * @return
	 */
	public List<Solve> getRank(String problemId);

	public Integer getSolveCount(String problemId);


	/**
	 * 用户提交做题信息
	 *
	 * @param solveInfo
	 * @return
	 */
	public Boolean submitSolve(SolveInfo solveInfo);

	/**
	 * 判断Aid用户是否做过Pid题目
	 *
	 * @param Pid
	 * @param Aid
	 * @return
	 */
	public Boolean solveJudge(String Pid, String Aid);

	public String getMinSolveTime(String Pid);

}
