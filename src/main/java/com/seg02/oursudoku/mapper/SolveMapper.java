package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Solve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
public interface SolveMapper extends BaseMapper<Solve> {


	/**
	 * 根据题面id获取通过排名
	 *
	 * @param problemId
	 * @return
	 */
	public List<Solve> selectRank(@Param("problemId") String problemId);

	public int selectSolveCount(@Param("Pid") String Pid);

	/**
	 * 提交用户通过记录
	 *
	 * @param Pid
	 * @param Aid
	 * @param time
	 */
	public Boolean insertSolve(@Param("Pid") String Pid, @Param("Aid") String Aid, @Param("time") Integer time);


	/**
	 * 判断用户是否做过Pid的题目
	 *
	 * @param Aid
	 * @param Pid
	 * @return
	 */
	public Integer selectSolve(@Param("Aid") String Aid, @Param("Pid") String Pid);

	public Integer selectMinSolve(@Param("Pid") String Pid);
}
