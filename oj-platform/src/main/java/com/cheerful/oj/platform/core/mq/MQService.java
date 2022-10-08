package com.cheerful.oj.platform.core.mq;

/**
 * @Copyright: www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date: 10/8/22 2:17 PM
 * @Author: wangzhifu
 */
public interface MQService {

	void amqpSend(String exchangeName, String routerKey, Object data);

	void topicSend(String topic, Object data);

}
