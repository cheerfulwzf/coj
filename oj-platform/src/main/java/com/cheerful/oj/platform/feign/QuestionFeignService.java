package com.cheerful.oj.platform.feign;

import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.pojo.dto.Question;
import java.io.Serializable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("oj-question")
public interface QuestionFeignService {

	@GetMapping("/q/question/{id}")
	Result<Question> selectOne(@PathVariable Serializable id);
}
