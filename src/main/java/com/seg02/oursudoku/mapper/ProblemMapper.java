package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
public interface ProblemMapper extends BaseMapper<Problem> {
	List<Problem> selectProblemPage(@Param("level") Integer level);

	Boolean insertProblem(@Param("id") String id, @Param("level") Integer level, @Param("panes") String panes, @Param("info") String info);

	int selectMaxId();

	List<String> selectProblemByLevel(@Param("level") Integer level);

	Problem selectByPid(@Param("Pid") String Pid);

	List<String> selectProblemByAid(@Param("Aid") String Aid);


}
