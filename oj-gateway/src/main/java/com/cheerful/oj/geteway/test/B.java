package com.cheerful.oj.geteway.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/9 22:19
 * @DESCRIPTION:
 */

public class B {

    private A a;

    public A getA() {
        return a;
    }


    public void setA(A a) {
        this.a = a;
    }

    public B() {
        System.out.println("b success");
    }
}
