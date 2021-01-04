package com.seg02.oursudoku.res;

import com.seg02.oursudoku.info.RateInfo;
import lombok.Data;

import java.util.List;

@Data
public class RateLimitRes {
	List<RateInfo> rateInfoList;
}
