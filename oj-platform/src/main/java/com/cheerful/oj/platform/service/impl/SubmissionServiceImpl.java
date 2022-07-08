package com.cheerful.oj.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.ResultCaseDTO;
import com.cheerful.oj.platform.dao.SubmissionDao;
import com.cheerful.oj.platform.entity.Submission;
import com.cheerful.oj.platform.service.SubmissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Submission)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
@Service("submissionService")
public class SubmissionServiceImpl extends ServiceImpl<SubmissionDao, Submission> implements SubmissionService {

}

