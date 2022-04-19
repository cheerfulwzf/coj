package com.cheerful.oj.judge.factory;

import com.cheerful.oj.judge.factory.impl.*;
import com.cheerful.oj.judge.exception.OrderTypeNotFoundException;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP11JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP14JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP17JudgeHandler;
import com.cheerful.oj.judge.factory.impl.gcc.GNUC90JudgeHandler;
import com.cheerful.oj.judge.factory.impl.gcc.GNUC99JudgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 13:59
 * @DESCRIPTION:
 */
@Component
public class JudgeFactory {
    private final JavaJudgeHandler java;

    private final GNUC90JudgeHandler c90;

    private final GNUC99JudgeHandler c99;

    private final GNUCPP11JudgeHandler cpp11;

    private final GNUCPP14JudgeHandler cpp14;

    private final GNUCPP17JudgeHandler cpp17;

    private final GoJudgeHandler go;

    private final JSJudgeHandler js;

    private final Python2JudgeHandler py2;

    private final Python3JudgeHandler py3;

    @Autowired
    public JudgeFactory(JavaJudgeHandler java, GNUC90JudgeHandler c90, GNUC99JudgeHandler c99, GNUCPP11JudgeHandler cpp11, GNUCPP14JudgeHandler cpp14, GNUCPP17JudgeHandler cpp17, GoJudgeHandler go, JSJudgeHandler js, Python2JudgeHandler py2, Python3JudgeHandler py3) {
        this.java = java;
        this.c90 = c90;
        this.c99 = c99;
        this.cpp11 = cpp11;
        this.cpp14 = cpp14;
        this.cpp17 = cpp17;
        this.go = go;
        this.js = js;
        this.py2 = py2;
        this.py3 = py3;
    }

    /**
     * 创建响应的语言的模板
     * @param orderType 选择哪种语言？
     * 0--->Java
     *
     * @return 最终各语言的判题
     */
    public JudgeHandler createJudgeHandler(Integer orderType){
        JudgeHandler judgeHandler=null;
        switch (orderType){
            case 0:
                judgeHandler=java;
                break;
            case 1:
                judgeHandler=c90;
                break;
            case 2:
                judgeHandler=c99;
                break;
            case 3:
                judgeHandler=cpp11;
                break;
            case 4:
                judgeHandler=cpp14;
                break;
            case 5:
                judgeHandler=cpp17;
                break;
            case 6:
                judgeHandler=go;
                break;
            case 8:
                judgeHandler=py2;
                break;
            case 9:
                judgeHandler=py3;
                break;
            case 10:
                judgeHandler=js;
                break;
            default:
                throw new OrderTypeNotFoundException("没找到对应的语言...");
        }
        return judgeHandler;
    }
}
