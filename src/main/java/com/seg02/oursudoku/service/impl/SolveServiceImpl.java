package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Solve;
import com.seg02.oursudoku.info.SolveInfo;
import com.seg02.oursudoku.mapper.SolveMapper;
import com.seg02.oursudoku.service.ISolveService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-28
 */
@Service
public class SolveServiceImpl implements ISolveService {

	@Resource
	SolveMapper solveMapper;

	@Override
	public List<Solve> getRank(String problemId) {
		return solveMapper.selectRank(problemId);
	}

	@Override
	public Integer getSolveCount(String problemId) {
		return solveMapper.selectSolveCount(problemId);
	}

	@Override
	public Boolean submitSolve(SolveInfo solveInfo) {
		return solveMapper.insertSolve(solveInfo.getProblemId(), solveInfo.getAccountId(), solveInfo.getSolveCostTime());
	}

	@Override
	public Boolean solveJudge(String Pid, String Aid) {
		Boolean ans = solveMapper.selectSolve(Pid, Aid);
		if (ans == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getMinSolveTime(String Pid) {
		int t = solveMapper.selectMinSolve(Pid);
		int h, m;
		h = t / 60;
		m = t % 60;
		return String.format("%02d'%02d''", h, m);
	}
}
