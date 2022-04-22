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

    @Override
    public void updateDetails(JudgeResultDTO res) {
        // TODO: 2022/3/27 有待优化成updateByInfo，省去一次查询。懒得写了
        Submission submission = this.getById(res.getSubmissionId());
        if (res.getGlobalMsg()==null) {
            List<ResultCaseDTO> results = res.getResult();
            int resultCode=-1;
            double timeUsed=-1,memUsed=-1;
            for (ResultCaseDTO result : results) {
                //得到最大的时间内存消耗，以及最终结果
                resultCode=Math.max(resultCode,result.getStatus());
                timeUsed=Math.max(result.getTimeUsed(),timeUsed);
                memUsed=Math.max(result.getMemoryUsed(),timeUsed);
            }
            submission.setResultCode(resultCode);
            submission.setResultMsg(JudgeStatusConstant.getMsgByCode(resultCode));
            submission.setResultInfo(JSON.toJSONString(res.getResult()));
            submission.setTimeUsed(timeUsed);
            submission.setMemoryUsed(memUsed);
        }else{
            submission.setResultInfo(res.getGlobalMsg());
            submission.setResultCode(JudgeStatusConstant.CE.getCode());
            submission.setResultMsg(JudgeStatusConstant.CE.getMsg());
        }
        this.updateById(submission);
    }
}
