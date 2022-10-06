package com.cheerful.oj.platform.config;

import javax.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 15:25
 * @DESCRIPTION:
 */
@Configuration
public class RabbitMqConfig {

	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * RabbitMQ消息序列化
	 *
	 * @return MessageConverter
	 */
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * 配置消息可靠性
	 */
	@PostConstruct
	public void initRabbitTemplate() {
		rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
			// TODO: 2022/3/27 LOG 消息确认失败
		});
		rabbitTemplate.setReturnCallback((message, i, s, s1, s2) -> {
			// TODO: 2022/3/27 LOG 消息推送失败可以保存进db或者redis，后面定时任务重试
		});
	}
}
