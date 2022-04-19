package com.cheerful.oj.geteway.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/9 22:18
 * @DESCRIPTION:
 */
public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A() {
        System.out.println("a success");
    }
}
