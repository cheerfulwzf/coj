package com.cheerful.oj.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheerful.oj.question.entity.Question;
import java.util.List;

/**
 * (Question)表服务接口
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
public interface QuestionService extends IService<Question> {

	IPage<Question> pageInfo(Page<Question> page, QueryWrapper<Question> questionQueryWrapper);

	/**
	 * 保存题目同时保存关系表
	 *
	 * @param question
	 * @return
	 */
	Boolean saveInfo(Question question);

	/**
	 * 删除时还要删除关系表
	 *
	 * @param idList
	 * @return
	 */
	Boolean remove(List<Long> idList);
}

