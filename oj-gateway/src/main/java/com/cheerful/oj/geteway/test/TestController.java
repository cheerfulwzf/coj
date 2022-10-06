package com.cheerful.oj.geteway.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/9 22:45
 * @DESCRIPTION:
 */
public class TestController {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		A a = context.getBean("a", A.class);
		B b = context.getBean("b", B.class);
	}
}
