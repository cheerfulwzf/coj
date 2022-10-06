package com.cheerful.oj.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheerful.oj.platform.entity.UserLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (UserLoginLog)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-10 17:04:24
 */
public interface UserLoginLogDao extends BaseMapper<UserLoginLog> {

	/**
	 * 批量新增数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<UserLoginLog> 实例对象列表
	 * @return 影响行数
	 */
	int insertBatch(@Param("entities") List<UserLoginLog> entities);

	/**
	 * 批量新增或按主键更新数据（MyBatis原生foreach方法）
	 *
	 * @param entities List<UserLoginLog> 实例对象列表
	 * @return 影响行数
	 * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
	 */
	int insertOrUpdateBatch(@Param("entities") List<UserLoginLog> entities);

}

