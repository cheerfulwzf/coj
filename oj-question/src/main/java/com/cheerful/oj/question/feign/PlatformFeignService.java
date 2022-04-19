package com.cheerful.oj.question.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("oj-platform")
public interface PlatformFeignService {

}
