package com.cheerful.oj.judge.factory.impl.cpp;

import com.cheerful.oj.judge.factory.base.CPPJudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/3 21:58
 * @DESCRIPTION:
 */
@Service
public class GNUCPP14JudgeHandler extends CPPJudgeHandler {

  @Value("${judge.GNUCPP14.compilerCmd}")
  private String compilerWord;

  @Override
  protected ExecutorUtil.ExecMessage handlerCompiler(File path) {
    String cmd = compilerWord.replace("PATH", path.getPath());
    return ExecutorUtil.exec(cmd, 5000);
  }
}
