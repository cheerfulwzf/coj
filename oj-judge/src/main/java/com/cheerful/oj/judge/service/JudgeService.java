package com.cheerful.oj.judge.service;

import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.JudgeTaskDTO;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:42
 * @DESCRIPTION:
 */
public interface JudgeService {
    void judge(JudgeTaskDTO task);
}
