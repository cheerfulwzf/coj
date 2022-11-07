package com.cheerful.oj.judge.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.dto.ResultCaseDTO;
import com.cheerful.oj.judge.core.factory.JudgeFactory;
import com.cheerful.oj.judge.core.factory.base.JudgeHandler;
import com.cheerful.oj.judge.core.service.JudgeService;
import com.cheerful.oj.judge.entity.Submission;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:45
 * @DESCRIPTION:
 */
@Slf4j
@Service
public class JudgeServiceImpl implements JudgeService {

	@Autowired
	private JudgeFactory judgeFactory;

	@Resource(name = "MyThreadPoolExecutor")
	private ThreadPoolExecutor executor;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void judge(JudgeTaskDTO task) {
		CompletableFuture.runAsync(() -> doJudge(task), executor);
	}

	private void doJudge(JudgeTaskDTO task) {
		JudgeHandler handler = judgeFactory.createJudgeHandler(task.getOrderType());
		JudgeResultDTO res = handler.judge(task);
		Long submissionId = task.getSubmissionId();
		res.setSubmissionId(submissionId);
		// 判题完成
		updateDetails(submissionId, task.getCallback(), res);
	}

	public void updateDetails(Long submissionId, String callback, JudgeResultDTO res) {
		log.info("C语言判题完成, submissionId:{}, result:{}", submissionId, res);
		Submission submission;
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
			submission = afterJudgeBuild(submissionId, resultCode, JSON.toJSONString(res.getResult()), timeUsed,
				memUsed);
		} else {
			submission = afterJudgeBuild(submissionId, JudgeStatusConstant.RE.getCode(), res.getGlobalMsg(), timeUsed,
				memUsed);
		}
		restTemplate.put(callback, submission);
	}

	private Submission afterJudgeBuild(Long submissionId,
		Integer resultCode, String resultInfo, Double timeUsed, Double memUsed) {
		return Submission.builder()
			.id(submissionId)
			.resultCode(resultCode)
			.resultInfo(resultInfo)
			.timeUsed(timeUsed)
			.memoryUsed(memUsed)
			.build();
	}
}
