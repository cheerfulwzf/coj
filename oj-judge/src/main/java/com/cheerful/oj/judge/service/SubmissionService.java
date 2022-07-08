package com.cheerful.oj.judge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.judge.entity.Submission;

/**
 * (Submission)表服务接口
 *
 * @author makejava
 * @since 2022-06-22 20:20:18
 */
public interface SubmissionService extends IService<Submission> {
    /**
     * 更新判题结果
     *
     * @param submission 原来存入的的判题记录
     * @param res c语言执行后的判题结果
     */
    void updateDetails(Submission submission,JudgeResultDTO res);
}

