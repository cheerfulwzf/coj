package com.cheerful.oj.platform.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/30 15:25
 * @DESCRIPTION: 参数检查
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Verify {

	/**
	 * 参数名称
	 */
	String name();

	/**
	 * 参数最大长度
	 */
	int maxLength() default Integer.MAX_VALUE;

	/**
	 * 是否必填 这里只是判断是否为null
	 */
	boolean required() default true;

	/**
	 * 是否为非空 是否为null和空串都判断
	 */
	boolean notNull() default true;

	/**
	 * 最小长度
	 */
	int minLength() default Integer.MIN_VALUE;

	/**
	 * 正则匹配
	 */
	String regular() default "";
}