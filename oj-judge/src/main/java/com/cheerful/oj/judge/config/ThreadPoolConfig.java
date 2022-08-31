package com.cheerful.oj.judge.config;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 15:14
 * @DESCRIPTION: 自定义线程池
 */
@Configuration
public class ThreadPoolConfig {

  @Qualifier("MyThreadPoolExecutor")
  @Bean(name = "MyThreadPoolExecutor")
  public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties poolProperties) {
    return new ThreadPoolExecutor(
      poolProperties.getCoreSize(),
      poolProperties.getMaxSize(),
      poolProperties.getKeepAliveTime(), TimeUnit.MILLISECONDS,
      new LinkedBlockingDeque<>(poolProperties.getQueueLength()),
      Executors.defaultThreadFactory(),
      new ThreadPoolExecutor.AbortPolicy()
    );
  }
}
