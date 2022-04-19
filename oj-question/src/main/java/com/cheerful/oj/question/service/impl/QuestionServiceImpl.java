package com.cheerful.oj.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.question.dao.QuestionDao;
import com.cheerful.oj.question.entity.QmaQTagRelation;
import com.cheerful.oj.question.entity.Question;
import com.cheerful.oj.question.entity.Tag;
import com.cheerful.oj.question.service.QuestionService;
import com.cheerful.oj.question.service.TagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Question)表服务实现类
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {
    @Autowired
    TagRelationService relationService;

    @Override
    public IPage<Question> pageInfo(Page<Question> page, QueryWrapper<Question> questionQueryWrapper) {
        IPage<Question> res = this.page(page);
        res.getRecords().forEach(record->{
            Long qid = record.getId();
            List<String> tags=relationService.selectRelationTagName(qid);
            record.setTags(tags);
        });
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveInfo(Question question) {
        this.save(question);
        Long qid = question.getId();
        List<String> tags = question.getTags();
        List<QmaQTagRelation> list=new ArrayList<>();
        if(tags!=null && tags.size()>0){
            tags.forEach(tag->{
                QmaQTagRelation record = new QmaQTagRelation();
                record.setQid(qid);
                String[] split = tag.split(":");
                record.setTid(Long.parseLong(split[0]));
                record.setTValue(split[1]);
                list.add(record);
            });
            relationService.saveBatch(list);
        }
        return true;
    }

    @Override
    public Boolean remove(List<Long> idList) {
        this.removeByIds(idList);
        relationService.remove(new QueryWrapper<QmaQTagRelation>().in("qid",idList));
        return true;
    }
}

