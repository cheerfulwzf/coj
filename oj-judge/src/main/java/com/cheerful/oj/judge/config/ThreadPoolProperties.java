package com.cheerful.oj.judge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 15:14
 * @DESCRIPTION: 配置线程池参数
 */
@Data
@Component
@ConfigurationProperties(prefix = "oj.thread")
public class ThreadPoolProperties {
  private Integer coreSize;
  private Integer maxSize;
  private Integer keepAliveTime;
  private Integer queueLength;
}
