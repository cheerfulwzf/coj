package com.cheerful.oj.judge.listener;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.service.JudgeService;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 16:13
 * @DESCRIPTION:
 */
@Service
@Slf4j
@RabbitListener(queues = "judge.wait.queue")
public class JudgeRabbitListener {

	private final JudgeService judgeService;

	public JudgeRabbitListener(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	/**
	 * 收到需要判题的消息，消费消息判题，判题成功回传消息到消息队列告诉其结果
	 *
	 * <b>#这里我们应该使用手动ack机制，以免任务失败消息丢失</b>
	 *
	 * @param task    消息内容
	 * @param message 消息信息
	 * @param channel 消息通道，ack通知
	 */
	@RabbitHandler
	public void handleJudgeTask(JudgeTaskDTO task, Message message, Channel channel)
		throws IOException {
		try {
			judgeService.judge(task);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			log.info("判题发生错误：{}", e.getMessage());
			e.printStackTrace();
			//消息消费失败，重回队列/本地表
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
		}
	}
}
