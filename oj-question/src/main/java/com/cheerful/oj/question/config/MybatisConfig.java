package com.cheerful.oj.question.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 15:23
 * @DESCRIPTION:
 */
@MapperScan("com.cheerful.oj.question.dao")
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements MetaObjectHandler {

	/**
	 * 分页插件
	 *
	 * @return 超过最大页码返回首页，每页最多1000条
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor().setOverflow(true).setLimit(1000);
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("gmtCreate", new Date(), metaObject);
		this.setFieldValByName("gmtModified", new Date(), metaObject);
		this.setFieldValByName("submitCount", 0, metaObject);
		this.setFieldValByName("isShow", 1, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("gmtModified", new Date(), metaObject);
	}
}
