package com.seg02.oursudoku.util;


import com.seg02.oursudoku.entity.Rate;
import com.seg02.oursudoku.entity.RoomMember;
import com.seg02.oursudoku.info.RoomMemberInfo;
import com.seg02.oursudoku.mapper.RateMapper;
import com.seg02.oursudoku.mapper.RoomMemberMapper;
import com.seg02.oursudoku.service.IRateService;
import com.seg02.oursudoku.service.IRoomMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Configuration
@EnableScheduling
public class RateRefresh {

	@Autowired
	IRateService iRateService;

	@Scheduled(cron = "00 0,30 * * * *")
	public void solve() {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(df.format(new Date()));
		iRateService.Refresh();
	}
}
