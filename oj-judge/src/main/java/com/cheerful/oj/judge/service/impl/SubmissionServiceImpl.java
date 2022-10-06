package com.cheerful.oj.judge.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.ResultCaseDTO;
import com.cheerful.oj.judge.dao.SubmissionDao;
import com.cheerful.oj.judge.entity.Submission;
import com.cheerful.oj.judge.service.SubmissionService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * (Submission)表服务实现类
 *
 * @author makejava
 * @since 2022-06-22 20:20:18
 */
@Service("submissionService")
public class SubmissionServiceImpl extends ServiceImpl<SubmissionDao, Submission> implements
	SubmissionService {

	@Override
	public void updateDetails(Submission submission, JudgeResultDTO res) {
		int resultCode = -1;
		double timeUsed = -1, memUsed = -1;
		if (StringUtils.isEmpty(res.getGlobalMsg())) {
			List<ResultCaseDTO> results = res.getResult();
			for (ResultCaseDTO result : results) {
				//得到最大的时间内存消耗，以及最终结果
				resultCode = Math.max(resultCode, result.getStatus());
				timeUsed = Math.max(result.getTimeUsed(), timeUsed);
				memUsed = Math.max(result.getMemoryUsed(), timeUsed);
			}
			afterJudgeBuild(submission, resultCode, JSON.toJSONString(res.getResult()), timeUsed,
				memUsed);
		} else {
			afterJudgeBuild(submission, JudgeStatusConstant.RE.getCode(), res.getGlobalMsg(), timeUsed,
				memUsed);
		}
		// 这里最好加上乐观锁保证线程安全，
		this.updateById(submission);
	}

	private void afterJudgeBuild(Submission submission,
		Integer resultCode, String resultInfo, Double timeUsed, Double memUsed) {
		submission.setResultCode(resultCode);
		submission.setResultMsg(JudgeStatusConstant.getMsgByCode(resultCode));
		submission.setResultInfo(resultInfo);
		submission.setTimeUsed(timeUsed);
		submission.setMemoryUsed(memUsed);
	}
}

