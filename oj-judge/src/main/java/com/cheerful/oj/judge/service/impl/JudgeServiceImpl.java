package com.cheerful.oj.judge.service.impl;

import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.entity.Submission;
import com.cheerful.oj.judge.factory.JudgeFactory;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.service.JudgeService;
import com.cheerful.oj.judge.service.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  JudgeFactory judgeFactory;

  @Autowired
  SubmissionService submissionService;

  @Override
  public void judge(JudgeTaskDTO task) {
    Submission submission = submissionService.getById(task.getSubmissionId());
    //避免消息重复消费 消费端幂等性
    if (!submission.getResultCode().equals(JudgeStatusConstant.BLOCK.getCode())) {
      return;
    }
    JudgeHandler handler = judgeFactory.createJudgeHandler(task.getOrderType());
    if (handler == null) {
      return;
    }
    JudgeResultDTO res = handler.judge(task);
    res.setSubmissionId(task.getSubmissionId());
    //发送消息表示判题完成
    log.info("C语言判题完成：{}", res);
    submissionService.updateDetails(submission, res);
  }
}
