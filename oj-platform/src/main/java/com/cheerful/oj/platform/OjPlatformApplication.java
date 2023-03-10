package com.cheerful.oj.platform;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableKafka
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
@EnableRedisHttpSession
@SpringBootApplication
public class OjPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjPlatformApplication.class, args);
	}

}
