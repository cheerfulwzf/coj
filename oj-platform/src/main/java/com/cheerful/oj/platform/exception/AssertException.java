package com.cheerful.oj.platform.exception;

import com.cheerful.oj.common.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/31 10:45
 * @DESCRIPTION: 假言截取自定义错误
 */
@Slf4j
public class AssertException {

	public static void assertMethod(boolean status, String msg) throws Exception {
		//为false抛出异常
		if (!status) {
			//记录错误信息
			log.error(msg);
			//抛出异常
			throw ParamException.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg)
				.build();
		}
	}

	/**
	 * 如果为空直接抛出异常 类似于断言的思想
	 *
	 * @param status 当status为false 就会抛出异常 不继续执行后续语句
	 * @param code   状态码
	 * @param msg    异常描述
	 */
	public static void assertMethod(boolean status, Integer code, String msg) throws Exception {
		//为false抛出异常
		if (!status) {
			//记录错误信息
			log.error(msg);
			//抛出异常
			throw ParamException.builder()
				.code(code)
				.msg(msg)
				.build();
		}
	}

	/**
	 * @param status 当status为false 就会抛出异常 不继续执行后续语句
	 */
	public static void assertMethod(boolean status) throws Exception {
		//为false抛出异常
		if (!status) {
			//记录错误信息
			log.error(HttpStatus.INTERNAL_SERVER_ERROR.name());
			//抛出异常
			throw ParamException.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.msg(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.build();
		}
	}
}
