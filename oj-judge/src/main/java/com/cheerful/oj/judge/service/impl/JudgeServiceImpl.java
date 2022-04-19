package com.cheerful.oj.judge.service.impl;

import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.factory.JudgeFactory;
import com.cheerful.oj.judge.service.JudgeService;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:45
 * @DESCRIPTION:
 */
@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    JudgeFactory judgeFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void judge(JudgeTaskDTO task) {
        JudgeHandler handler;
        handler = judgeFactory.createJudgeHandler(task.getOrderType());
        JudgeResultDTO res = handler.judge(task);
        res.setSubmissionId(task.getSubmissionId());
        //发送消息表示判题完成
        System.out.println("判题完成："+res);
        rabbitTemplate.convertAndSend("judge-event-exchange","judge.finish",res);
    }
}
