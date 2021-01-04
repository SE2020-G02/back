package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.ProblemInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
public interface IProblemService extends IService<Problem> {

	/**
	 * 根据等级level查询count数量的数独题面
	 *
	 * @param level
	 * @param count
	 * @return
	 */
	public List<ProblemInfo> findByLevel(Integer level, Integer count);

	/**
	 * 将path路径文件中的题面添加到数据库中，题面等级为level
	 *
	 * @param path
	 * @param level
	 * @return
	 */
	public Boolean insertProblems(String path, Integer level);

	public ProblemInfo findByPid(String Pid);

	public ProblemInfo findRandom(String Aid);

	public Boolean creatProblem(ProblemInfo problemInfo);

}
