package com.seg02.oursudoku.service;

import com.seg02.oursudoku.entity.Rate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seg02.oursudoku.info.RateInfo;
import com.seg02.oursudoku.res.RateStaticRes;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
public interface IRateService extends IService<Rate> {

	/**
	 * 获取第ratePage页的rateCount个排行榜
	 *
	 * @return
	 */
	public List<RateInfo> getByCount();

	public Integer getPageCount(Integer rateCount);

	public RateStaticRes getStaticMessage();

	public void Refresh();

}
