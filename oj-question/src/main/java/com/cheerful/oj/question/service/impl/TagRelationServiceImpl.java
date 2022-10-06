package com.cheerful.oj.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.question.dao.QmaQTagRelationDao;
import com.cheerful.oj.question.entity.QmaQTagRelation;
import com.cheerful.oj.question.service.TagRelationService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * (QmaQTagRelation)表服务实现类
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
@Service("qmaQTagRelationService")
public class TagRelationServiceImpl extends
	ServiceImpl<QmaQTagRelationDao, QmaQTagRelation> implements TagRelationService {

	@Override
	public List<String> selectRelationTagName(Long qid) {
		return this.baseMapper.selectRelationTagName(qid);
	}
}

