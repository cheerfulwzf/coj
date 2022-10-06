package com.cheerful.oj.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheerful.oj.question.entity.QmaQTagRelation;
import java.util.List;

/**
 * (QmaQTagRelation)表服务接口
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
public interface TagRelationService extends IService<QmaQTagRelation> {

	/**
	 * 查找题目关联的标签名
	 *
	 * @param qid
	 * @return
	 */
	List<String> selectRelationTagName(Long qid);
}

