package com.seg02.oursudoku.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.seg02.oursudoku.info.BlogInfo;
import com.seg02.oursudoku.util.MyBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2020-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Blogs implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("blog_ID")
	private String blogId;

	@TableField("blo_blog_ID")
	private String bloBlogId;

	@TableField("problem_ID")
	private String problemId;

	private String blogMessage;

	private LocalDateTime blogUpdateTime;

	public BlogInfo change() {
		BlogInfo blogInfo = new BlogInfo();
		MyBeanUtils.copyProperties(this, blogInfo);
		return blogInfo;
	}


}
