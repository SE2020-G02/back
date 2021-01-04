package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.AccountPublicInfo;
import com.seg02.oursudoku.info.BlogInfo;
import lombok.Data;

@Data
public class BlogRes {
	private BlogInfo blogInfo;

	private AccountPublicInfo accountPublicInfo;
}
