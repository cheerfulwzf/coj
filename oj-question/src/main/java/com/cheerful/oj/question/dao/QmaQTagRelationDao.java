package com.cheerful.oj.question.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheerful.oj.question.entity.QmaQTagRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (QmaQTagRelation)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
public interface QmaQTagRelationDao extends BaseMapper<QmaQTagRelation> {

	/**
	 * 批量新增数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<QmaQTagRelation> 实例对象列表
	 * @return 影响行数
	 */
	int insertBatch(@Param("entities") List<QmaQTagRelation> entities);

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<QmaQTagRelation> 实例对象列表
	 * @return 影响行数
	 * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
	 */
	int insertOrUpdateBatch(@Param("entities") List<QmaQTagRelation> entities);

	List<String> selectRelationTagName(@Param("qid") Long qid);
}

