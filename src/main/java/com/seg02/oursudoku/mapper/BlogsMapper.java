package com.seg02.oursudoku.mapper;

import com.seg02.oursudoku.entity.Blogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
public interface BlogsMapper extends BaseMapper<Blogs> {

	Blogs selectByPid(@Param("Pid") String Pid);

	Blogs selectByBid(@Param("Bid") String Bid);

	List<String> selectAllByPid(@Param("Pid") String Pid);

	String selectAid(@Param("Bid") String Bid);

	String selectPid(@Param("Bid") String Bid);

	int selectMaxBid();

	void insertBlog(@Param("Bid") String Bid, @Param("BBid") String BBid, @Param("Pid") String Pid, @Param("Message") String message);

	void insertBlogRelation(@Param("Bid") String Bid, @Param("Aid") String Aid, @Param("type") String type, @Param("state") String state);

}
