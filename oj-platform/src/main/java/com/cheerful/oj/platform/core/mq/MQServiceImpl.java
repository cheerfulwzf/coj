package com.cheerful.oj.platform.core.mq;

import com.cheerful.oj.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Copyright: www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date: 10/8/22 2:21 PM
 * @Author: wangzhifu
 */
@Slf4j
@Service
public class MQServiceImpl implements MQService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaProducer;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void amqpSend(String exchangeName, String routerKey, Object data) {
		rabbitTemplate.convertAndSend(exchangeName, routerKey, data);
	}

	@Override
	public void topicSend(String topic, Object data) {
		kafkaProducer.send(topic, data).addCallback(
			success -> {
				if (success != null) {
					String topicName = success.getRecordMetadata().topic();
					int partition = success.getRecordMetadata().partition();
					long offset = success.getRecordMetadata().offset();
					log.info("send success, topic:{}, partition:{}, offset:{}, message:{}", topicName,
						partition,
						offset, JsonUtil.toJsonString(data));
				}
			},
			err -> log.info("send error, topic:{}, message:{} ,errorMsg:{}", topic,
				JsonUtil.toJsonString(data),
				err.getCause())
		);
	}
}
