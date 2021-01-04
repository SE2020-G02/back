package com.seg02.oursudoku.service.impl;

import com.seg02.oursudoku.entity.Problem;
import com.seg02.oursudoku.info.ProblemInfo;
import com.seg02.oursudoku.mapper.ProblemMapper;
import com.seg02.oursudoku.mapper.SolveMapper;
import com.seg02.oursudoku.service.IProblemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zx
 * @since 2020-12-27
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

	@Resource
	ProblemMapper problemMapper;

	@Resource
	SolveMapper solveMapper;

	@Override
	public List<ProblemInfo> findByLevel(Integer level, Integer count) {
		List<Problem> problemList = problemMapper.selectProblemPage(level);
		Collections.shuffle(problemList);
		List<ProblemInfo> ans = new ArrayList<ProblemInfo>();
		for (int i = 0; i < Math.min(problemList.size(), count); i++) {
			ans.add(problemList.get(i).change());
			ans.get(i).setProblemSolveCount(solveMapper.selectSolveCount(ans.get(i).getProblemId()));
		}
		return ans;
	}

	@Override
	public Boolean insertProblems(String path, Integer level) {
		try {
			File file = new File(path);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String s = bufferedReader.readLine();
			int x = Integer.parseInt(s);
			int id = problemMapper.selectMaxId() + 1;
			for (int i = 0; i < x; i++) {
				s = bufferedReader.readLine();
				problemMapper.insertProblem(String.format("%04d", Integer.parseInt(id + i + "")), level, s, "这里什么都没有");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ProblemInfo findByPid(String Pid) {
		Problem problem = problemMapper.selectByPid(Pid);
		if (problem != null) {
			return problem.change();
		} else {
			return null;
		}
	}

	@Override
	public ProblemInfo findRandom(String Aid) {
		List<String> Pids = problemMapper.selectProblemByAid(Aid);
		return problemMapper.selectByPid(Pids.get((int) (Math.random() % Pids.size()))).change();
	}

	@Override
	public Boolean creatProblem(ProblemInfo problemInfo) {
		problemMapper.insertProblem(problemInfo.getProblemId(),0,problemInfo.getProblemPanes(),problemInfo.getProblemInfo());
		return problemMapper.selectByPid(problemInfo.getProblemId())!=null;
	}
}
