package com.cheerful.oj.question.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheerful.oj.question.entity.Question;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (Question)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
public interface QuestionDao extends BaseMapper<Question> {

	/**
	 * 批量新增数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<Question> 实例对象列表
	 * @return 影响行数
	 */
	int insertBatch(@Param("entities") List<Question> entities);

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<Question> 实例对象列表
	 * @return 影响行数
	 * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
	 */
	int insertOrUpdateBatch(@Param("entities") List<Question> entities);

}

