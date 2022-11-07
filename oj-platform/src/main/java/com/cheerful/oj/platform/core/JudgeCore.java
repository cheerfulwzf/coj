package com.cheerful.oj.platform.core;

import com.cheerful.oj.common.constant.CachePrefixConstant;
import com.cheerful.oj.common.constant.HttpStatusConstant;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.util.IpUtil;
import com.cheerful.oj.common.util.UuidUtil;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.core.mq.MQService;
import com.cheerful.oj.platform.entity.Submission;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.feign.QuestionFeignService;
import com.cheerful.oj.platform.interceptor.LoginUserInterceptor;
import com.cheerful.oj.platform.pojo.dto.Question;
import com.cheerful.oj.platform.pojo.vo.JudgeTaskVO;
import com.cheerful.oj.platform.service.SubmissionService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/12 15:25
 * @DESCRIPTION:
 */
@RestController
public class JudgeCore {

	@Autowired
	private MQService mqService;

	@Autowired
	private QuestionFeignService questionFeignService;

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Value("${server.port}")
	private Integer port;

	@Value("${server.servlet.context-path}")
	private String serverPrefix;

	/**
	 * 对前端暴露接口，增加判题任务到消息队列，保证接口幂等
	 *
	 * <h1>发送消息，保存判题记录</h1>
	 *
	 * @param task 判题任务
	 */
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("add/judge")
	public Result<Submission> judge(@RequestBody JudgeTaskVO task) {
		User user = LoginUserInterceptor.loginUser.get();
		String key = CachePrefixConstant.TOKEN_PREFIX + task.getUserId() + user.getId();
//        String key=CachePrefixConstant.TOKEN_PREFIX+task.getUserId()+task.getQid();
		if (checkLimitToken(key, task.getToken())) {
			return Result.success(HttpStatusConstant.TOKEN_NOT_FOUND, "请勿重复提交");
		}
		Long qid = task.getQid();
		Result<Question> result = questionFeignService.selectOne(qid);
		if (Objects.isNull(result.getData())) {
			return Result.error(HttpStatusConstant.QUESTION_NOT_FOUND, "题目id不存在");
		}

		// 调用远程接口封装判题任务信息
		Question question = result.getData();
		JudgeTaskDTO taskDTO = buildTaskDTO(task, qid, question);

		//提交判题记录
		Submission submission = Submission.builder()
			.questionId(task.getQid())
			.language(task.getOrderType())
			.source(task.getSource())
			.userId(user.getId())
			.nickname(user.getNickname())
			.questionTitle(question.getTitle())
			.resultCode(JudgeStatusConstant.BLOCK.getCode())
			.build();

		submissionService.save(submission);

		taskDTO.setSubmissionId(submission.getId());
		mqService.amqpSend("judge-event-exchange", "judge.wait", taskDTO);
		return Result.success(submission);
	}

	private JudgeTaskDTO buildTaskDTO(JudgeTaskVO task, Long qid, Question question) {
		String inSamples = question.getInSamples();
		String outSamples = question.getOutSamples();
		String[] input = inSamples.split(":");
		String[] output = outSamples.split(":");
		return JudgeTaskDTO.builder()
			.input(Arrays.asList(input))
			.output(Arrays.asList(output))
			.source(task.getSource())
			.orderType(task.getOrderType())
			.timeLimit(question.getTimeLimit())
			.memoryLimit(question.getMemoryLimit())
			.qid(qid)
			.callback(getCallbackUrl(IpUtil.getLocalHost()))
			.build();
	}

	private String getCallbackUrl(String ip) {
		return "http://" + ip + ":" + port + "/" + serverPrefix + "/submission";
	}

	/**
	 * 保证接口防抖 在redis植入一个`令牌`，前端传输来的任务中会包含这一项。 先去尝试`令牌`是否还存在，如不在了说明有人处理过了
	 *
	 * @param qid 题目id
	 * @return token
	 */
	@GetMapping("/getToken/{qid}")
	private String getToken(@PathVariable String qid) {
		User user = LoginUserInterceptor.loginUser.get();
		String token = UuidUtil.getId();
		redisTemplate.opsForValue()
			.setIfAbsent(CachePrefixConstant.TOKEN_PREFIX + qid + user.getId(), token);
		return token;
	}

	private boolean checkLimitToken(String key, String token) {
		if (redisTemplate.opsForValue().get(key) == null) {
			return true;
		}
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		return Objects.equals(redisTemplate.execute(
			new DefaultRedisScript<>(script, Long.class),
			Collections.singletonList(key),
			token
		), 0L);
	}
}
