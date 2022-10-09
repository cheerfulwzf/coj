package com.cheerful.oj.judge.listener;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.util.JsonUtil;
import com.cheerful.oj.judge.service.JudgeService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * @Copyright: www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date: 10/8/22 3:16 PM
 * @Author: wangzhifu
 */
@Slf4j
@Service
public class JudgeKafkaListener {

	private final JudgeService judgeService;

	public JudgeKafkaListener(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	@KafkaListener
	public void judgeTaskHandler(ConsumerRecord<String, String> msg, Acknowledgment ack) {
		Optional<String> s = Optional.ofNullable(msg.value());
		if (s.isPresent()) {
			try {
				String str = s.get();
				log.info("接到判题任务:{}", str);
				JudgeTaskDTO task = JsonUtil.fromJson(str, JudgeTaskDTO.class);
				judgeService.judge(task);
			} catch (Exception e) {
				// TODO: 10/9/22 失败的消息可以存在一个地方，这里先仅放一个日志
				log.error("task:{} error, \nerror details:{}", msg.value(), e.getCause());
			} finally {
				ack.acknowledge();
			}
		}
	}
}
