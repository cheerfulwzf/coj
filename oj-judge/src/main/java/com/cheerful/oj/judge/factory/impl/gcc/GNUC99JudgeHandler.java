package com.cheerful.oj.judge.factory.impl.gcc;

import com.cheerful.oj.judge.factory.base.CJudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/3 21:53
 * @DESCRIPTION:
 */
@Service
public class GNUC99JudgeHandler extends CJudgeHandler {
    @Value("${judge.GNUC99.compilerCmd}")
    private String compilerWord;

    @Override
    protected ExecutorUtil.ExecMessage handlerCompiler(File path) {
        String cmd = compilerWord.replace("PATH",path.getPath());
        return ExecutorUtil.exec(cmd, 5000);
    }
}
