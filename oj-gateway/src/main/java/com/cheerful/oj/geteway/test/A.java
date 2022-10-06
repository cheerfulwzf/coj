package com.cheerful.oj.geteway.test;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/9 22:18
 * @DESCRIPTION:
 */
public class A {

	private B b;

	public A() {
		System.out.println("a success");
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}
