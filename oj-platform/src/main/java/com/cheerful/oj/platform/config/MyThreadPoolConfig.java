package com.cheerful.oj.platform.config;

import com.cheerful.oj.platform.config.properties.ThreadPoolProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 15:14
 * @DESCRIPTION: 自定义线程池
 */
@Configuration
public class MyThreadPoolConfig {

    @Qualifier("MyThreadPoolExecutor")
    @Bean(name = "MyThreadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties poolProperties){
        return new ThreadPoolExecutor(
                poolProperties.getCoreSize(),
                poolProperties.getMaxSize(),
                poolProperties.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
