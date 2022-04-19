package com.cheerful.oj.platform.controller.core;

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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    /**
     * 对前端暴露接口，增加判题任务到消息队列
     * <h1>发送消息，保存判题记录</h1>
     * @param task 判题任务
     * @return
     */
    @PostMapping("add/judge")
    public Result<Submission> judge(@RequestBody JudgeTaskVO task){
        Long qid = task.getQid();
        JudgeTaskDTO taskDTO = new JudgeTaskDTO();
        Result<Question> result = questionFeignService.selectOne(qid);
        if (result.getData()==null){
            return Result.error();
        }

        //调用远程接口封装判题任务信息
        Question question = result.getData();
        buildTaskDTO(task, qid, taskDTO, question);

        //提交判题记录
        Submission submission = new Submission();
//        User user = LoginUserInterceptor.loginUser.get();
//        submission.setUserId(user.getId());
//        submission.setNickname(user.getNickname());
        submission.setLanguage(task.getOrderType());
        submission.setSource(task.getSource());
        submission.setUserId(task.getUserId());
        submission.setQuestionId(task.getQid());
        submission.setResultCode(JudgeStatusConstant.BLOCK.getCode());
        submission.setQuestionTitle(question.getTitle());
        submission.setNickname(task.getNickname());
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
}
