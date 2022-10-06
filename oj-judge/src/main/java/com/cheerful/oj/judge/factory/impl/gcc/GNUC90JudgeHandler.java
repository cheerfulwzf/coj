package com.cheerful.oj.judge.factory.impl.gcc;

import com.cheerful.oj.judge.factory.base.CJudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/26 15:53
 * @DESCRIPTION:
 */
@Component
public class GNUC90JudgeHandler extends CJudgeHandler {

	@Value("${judge.GNUC90.compilerCmd}")
	private String compilerWord;

	@Override
	protected ExecutorUtil.ExecMessage handlerCompiler(File path) {
		String cmd = compilerWord.replace("PATH", path.getPath());
		return ExecutorUtil.exec(cmd, 5000);
	}
}
