package com.seg02.oursudoku.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogInfo {

	private String blogId;

	private String bloBlogId;

	private String problemId;

	private String blogMessage;

	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime blogUpdateTime;
}
