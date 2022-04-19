package com.cheerful.oj.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.platform.entity.Submission;

/**
 * (Submission)表服务接口
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
public interface SubmissionService extends IService<Submission> {

    /**
     * 更新之间阻塞中的判题记录
     * @param res
     */
    void updateDetails(JudgeResultDTO res);
}

