package com.cheerful.oj.judge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.cheerful.oj.judge.dao")
public class OjJudgeApplication {

  public static void main(String[] args) {
    SpringApplication.run(OjJudgeApplication.class, args);
  }
}
