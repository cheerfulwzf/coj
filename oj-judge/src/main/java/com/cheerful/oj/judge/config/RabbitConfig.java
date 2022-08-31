package com.cheerful.oj.judge.config;

import javax.annotation.PostConstruct;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 16:30
 * @DESCRIPTION: todo 后续可以把router-key和queue-name等维护成Enum，有时间可以研究后替换为Kafka或者Pulsar
 */
@Configuration
public class RabbitConfig {

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
    rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
      @Override
      public void confirm(CorrelationData correlationData, boolean b, String s) {
        // TODO: 2022/3/27 LOG 消息确认失败
      }
    });
    rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
      @Override
      public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        // TODO: 2022/3/27 LOG 消息推送失败，可以保存到本地表，后续定时任务重试
      }
    });
  }

  /**
   * 判题服务默认的交换机
   *
   * @return
   */
  @Bean
  public Exchange judgeEventExchange() {
    //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
    return new TopicExchange("judge-event-exchange", true, false);
  }

//    /**
//     * 判题完成发送到此队列
//     * @return
//     */
//    @Bean
//    public Queue judgeFinishQueue() {
//        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
//        return new Queue("judge.finish.queue", true, false, false);
//    }

//    /**
//     * 交换机与判题完成队列绑定
//     * @return
//     */
//    @Bean
//    public Binding judgeFinishBind() {
//        //String destination, DestinationType destinationType, String exchange, String routingKey,
//        // 			Map<String, Object> arguments
//        Binding binding = new Binding("judge.finish.queue",
//                Binding.DestinationType.QUEUE,
//                "judge-event-exchange",
//                "judge.finish",
//                null);
//
//        return binding;
//    }

  /**
   * 等待判题的消息发送到此队列
   *
   * @return
   */
  @Bean
  public Queue judgeWaitQueue() {
    //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
    return new Queue("judge.wait.queue", true, false, false);
  }

  /**
   * 交换机与等待队列绑定
   *
   * @return
   */
  @Bean
  public Binding judgeWaitBind() {
    //String destination, DestinationType destinationType, String exchange, String routingKey,
    // 			Map<String, Object> arguments
    return new Binding("judge.wait.queue",
      Binding.DestinationType.QUEUE,
      "judge-event-exchange",
      "judge.wait",
      null);
  }
}
