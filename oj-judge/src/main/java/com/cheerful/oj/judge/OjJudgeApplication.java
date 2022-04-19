package com.cheerful.oj.judge;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class OjJudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjJudgeApplication.class, args);
    }
}
