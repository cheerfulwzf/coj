package com.cheerful.oj.platform.controller.core;

import com.cheerful.oj.common.constant.CachePrefixConstant;
import com.cheerful.oj.common.constant.HttpStatusConstant;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.entity.Submission;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.feign.QuestionFeignService;
import com.cheerful.oj.platform.interceptor.LoginUserInterceptor;
import com.cheerful.oj.platform.pojo.dto.Question;
import com.cheerful.oj.platform.pojo.vo.JudgeTaskVO;
import com.cheerful.oj.platform.service.SubmissionService;
import com.cheerful.oj.platform.util.UuidUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/12 15:25
 * @DESCRIPTION:
 */
@RestController
public class JudgeCore {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    QuestionFeignService questionFeignService;

    @Autowired
    SubmissionService submissionService;

    @Qualifier("MyThreadPoolExecutor")
    @Autowired
    ThreadPoolExecutor executor;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 对前端暴露接口，增加判题任务到消息队列，保证接口幂等
     *
     * <h1>发送消息，保存判题记录</h1>
     * @param task 判题任务
     *
     */
    @PostMapping("add/judge")
    public Result<Submission> judge(@RequestBody JudgeTaskVO task){
        User user = LoginUserInterceptor.loginUser.get();
        String key=CachePrefixConstant.TOKEN_PREFIX+task.getUserId()+user.getId();
//        String key=CachePrefixConstant.TOKEN_PREFIX+task.getUserId()+task.getQid();
        //如果token不存在则直接返回
        if (redisTemplate.opsForValue().get(key)==null){
            return Result.success(HttpStatusConstant.TOKEN_NOT_FOUND,"正在等待判题");
        }
        String script="if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long i = redisTemplate.execute(
                new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList(key),
                task.getToken()
        );
        System.out.println(i);
        if (i!=1L){
            return Result.success(HttpStatusConstant.TOKEN_NOT_FOUND,"请勿重复提交");
        }
        Long qid = task.getQid();
        Result<Question> result = questionFeignService.selectOne(qid);
        if (result.getData()==null){
            return Result.error(HttpStatusConstant.QUESTION_NOT_FOUND,"题目id不存在");
        }
        JudgeTaskDTO taskDTO = new JudgeTaskDTO();

        //调用远程接口封装判题任务信息
        Question question = result.getData();
        buildTaskDTO(task, qid, taskDTO, question);

        //提交判题记录
        Submission submission = new Submission();
        submission.setQuestionId(task.getQid());
//        submission.setUserId(task.getUserId());
//        submission.setNickname(task.getNickname());
        submission.setUserId(user.getId());
        submission.setNickname(user.getNickname());
        submission.setLanguage(task.getOrderType());
        submission.setSource(task.getSource());
        submission.setResultCode(JudgeStatusConstant.BLOCK.getCode());
        submission.setQuestionTitle(question.getTitle());
        submissionService.save(submission);

        taskDTO.setSubmissionId(submission.getId());
        rabbitTemplate.convertAndSend("judge-event-exchange",	"judge.wait",taskDTO);
        return Result.success(submission);
    }

    private void buildTaskDTO(JudgeTaskVO task, Long qid, JudgeTaskDTO taskDTO, Question question) {
        String inSamples = question.getInSamples();
        String outSamples = question.getOutSamples();
        String[] input = inSamples.split(":");
        String[] output = outSamples.split(":");
        taskDTO.setInput(Arrays.asList(input));
        taskDTO.setOutput(Arrays.asList(output));
        taskDTO.setSource(task.getSource());
        taskDTO.setTimeLimit(question.getTimeLimit());
        taskDTO.setMemoryLimit(question.getMemoryLimit());
        taskDTO.setQid(qid);
        taskDTO.setOrderType(task.getOrderType());
    }

    /**
     * 保证接口防抖
     *
     * @param userId 后端单元自测测试的时候使用
     * @return token
     */
    @GetMapping("/getTokenTest")
    private String getToken(@RequestParam Long userId,
                            @RequestParam Long qid){
        String token = UuidUtil.getId();
        redisTemplate.opsForValue().set(CachePrefixConstant.TOKEN_PREFIX+qid+userId,token);
        return token;
    }

    /**
     * 保证接口防抖
     * 在redis植入一个`令牌`，前端传输来的任务中会包含这一项。
     * 先去尝试`令牌`是否还存在，如不在了说明有人处理过了
     *
     * @param qid 题目id
     * @return token
     */
    @GetMapping("/getToken/{qid}")
    private String getToken(@PathVariable String qid){
        User user = LoginUserInterceptor.loginUser.get();
        String token = UuidUtil.getId();
        redisTemplate.opsForValue().setIfAbsent(CachePrefixConstant.TOKEN_PREFIX+qid+user.getId(),token);
        return token;
    }
}
