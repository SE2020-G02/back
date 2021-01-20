package com.seg02.oursudoku.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.seg02.oursudoku.entity.Rate;
import com.seg02.oursudoku.entity.RoomMember;
import com.seg02.oursudoku.entity.RoomMemberEnd;
import com.seg02.oursudoku.info.RateInfo;
import com.seg02.oursudoku.mapper.AccountMapper;
import com.seg02.oursudoku.mapper.RateMapper;
import com.seg02.oursudoku.mapper.RoomMemberMapper;
import com.seg02.oursudoku.res.RateStaticRes;
import com.seg02.oursudoku.service.IRateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-30
 */
@Service
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements IRateService {

	@Resource
	RateMapper rateMapper;

	@Resource
	AccountMapper accountMapper;

	@Resource
	RoomMemberMapper roomMemberMapper;

	@Override
	public List<RateInfo> getByCount() {
		List<Rate> rateList = rateMapper.selectByCount();
		List<RateInfo> ans = new ArrayList<RateInfo>();
		for (int i = 0; i < rateList.size(); i++) {
			ans.add(rateList.get(i).change());
			ans.get(i).setAccountNickname(accountMapper.selectNameById(ans.get(i).getAccountId()));
			ans.get(i).setAccountLevel(accountMapper.selectLevelById(ans.get(i).getAccountId()));
		}
		return ans;
	}

	@Override
	public Integer getPageCount(Integer rateCount) {
		return rateMapper.selectRateCount() / rateCount + 1;
	}

	@Override
	public RateStaticRes getStaticMessage() {
		RateStaticRes rateStaticRes = new RateStaticRes();
		rateStaticRes.setGameCount(rateMapper.getGameCount());
		rateStaticRes.setGamePeople(rateMapper.getGamePeople());
		return rateStaticRes;
	}

	@Override
	public void Refresh() {
		List<RoomMemberEnd> roomMemberList = roomMemberMapper.selectEndRoom();
		List<Rate> rateList = rateMapper.selectByCount();

		Map<String, Rate> rateMap = new HashMap<String, Rate>();
		for (int i = 0; i < rateList.size(); i++) {
			rateMap.put(rateList.get(i).getAccountId(), rateList.get(i));
		}

		for (int i = 0; i < roomMemberList.size(); i++) {
			if (roomMemberList.get(i).getTimecost() == 0) {
				roomMemberList.get(i).setTimecost(roomMemberList.get(i).getRoomRunningTime() * 60);
			}
			if (!rateMap.containsKey(roomMemberList.get(i).getAccountId())) {
				Rate rate = new Rate();
				rate.setAccountId(roomMemberList.get(i).getAccountId());
				rate.setRateAvgTime(0);
				rate.setRateGameCount(0);
				rate.setRateId(rateMap.size() + 1);
				rate.setRateNumber(1200);
				rate.setRateState("up");
				rateMap.put(rate.getAccountId(), rate);
			}
		}

		for (String key : rateMap.keySet()) {
			rateMap.get(key).setRateAvgTime(rateMap.get(key).getRateAvgTime() * rateMap.get(key).getRateGameCount());
		}

		RoomMemberEnd roomMember = new RoomMemberEnd();
		roomMember.setRoomId(-1);
		roomMemberList.add(roomMember);
		roomMemberList.add(roomMember);

		int gc = 0, pc = 0;

		List<RoomMemberEnd> game = new ArrayList<RoomMemberEnd>();
		for (int gi = 0; roomMemberList.get(gi).getRoomId() != -1; gi++) {
			game.add(roomMemberList.get(gi));
			if (!roomMemberList.get(gi).getRoomId().equals(roomMemberList.get(gi + 1).getRoomId())) {
				if (game.size() != 1) {
					Map<String, Integer> change = new HashMap<String, Integer>();
					for (int i = 0; i < game.size(); i++) {
						double s = 0, e = 0;
						for (int j = 0; j < game.size(); j++) {
							if (j == i) {
								continue;
							}
							e += (1 / (1 + Math.pow(10, (double) (rateMap.get(game.get(j).getAccountId()).getRateNumber() - rateMap.get(game.get(i).getAccountId()).getRateNumber()) / 400)));
							s += (1 / (1 + Math.pow(10, ((double) (game.get(i).getTimecost() - game.get(j).getTimecost()) / 400))));
						}
						int var = (int) (32.0 * (s - e));
						Rate rate = rateMap.get(game.get(i).getAccountId());
						if (var <= 0) {
							rate.setRateState("down");
						} else {
							rate.setRateState("up");
						}
						change.put(rate.getAccountId(), var);
					}

					for (String key : change.keySet()) {
						rateMap.get(key).setRateNumber(rateMap.get(key).getRateNumber() + change.get(key));
					}

					for (int i = 0; i < game.size(); i++) {
						if (game.size() != 1) {
							rateMap.get(game.get(i).getAccountId()).addAvgTime(game.get(i).getTimecost());
							rateMap.get(game.get(i).getAccountId()).addGameCount();
						}
					}
					if (game.size() != 1) {
						gc++;
						pc += game.size();
					}
				}
				roomMemberMapper.refresh(game.get(0).getRoomId());
				game.clear();
			}
		}

//		for (int gi = 0; roomMemberList.get(gi).getRoomId() != -1; gi++) {
//			if (roomMemberList.get(gi).getRoomRunningTime() * 60 != roomMemberList.get(gi).getTimecost()) {
//				rateMap.get(roomMemberList.get(gi).getAccountId()).addAvgTime(roomMemberList.get(gi).getTimecost());
//			}
//			rateMap.get(roomMemberList.get(gi).getAccountId()).addGameCount();
//		}


		rateList.clear();
		for (String key : rateMap.keySet()) {
			if (rateMap.get(key).getRateGameCount() != 0) {
				rateMap.get(key).setRateAvgTime(rateMap.get(key).getRateAvgTime() / rateMap.get(key).getRateGameCount());
			}
			rateList.add(rateMap.get(key));
		}

		Collections.sort(rateList, new Comparator<Rate>() {
			public int compare(Rate r1, Rate r2) {
				return r1.compareTo(r2);
			}
		});
		for (int i = 0; i < rateList.size(); i++) {
			rateList.get(i).setRateId(i + 1);
		}

		rateMapper.clear();
		rateMapper.insertList(rateList);
		rateMapper.updateInfo(gc, pc);

	}
}
