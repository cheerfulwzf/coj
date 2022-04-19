package com.cheerful.oj.platform.listener;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.ResultCaseDTO;
import com.cheerful.oj.platform.entity.Submission;
import com.cheerful.oj.platform.service.SubmissionService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 14:40
 * @DESCRIPTION:
 */
@Service
@RabbitListener(queues = "judge.finish.queue")
public class JudgeFinishListener {
    SubmissionService submissionService;

    @Autowired
    public JudgeFinishListener(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RabbitHandler
    public void handleWaitJudge(JudgeResultDTO res, Message message, Channel channel) throws IOException{
        try {
            submissionService.updateDetails(res);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
