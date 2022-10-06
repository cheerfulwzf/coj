package com.cheerful.oj.judge.factory.impl;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import com.cheerful.oj.judge.util.FileUtil;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 13:41
 * @DESCRIPTION:
 */
@Component
public class JavaJudgeHandler extends JudgeHandler {

	@Value("${judge.java.compilerCmd}")
	private String compilerCmd;

	@Value("${judge.java.runCmd}")
	private String runCmd;

	@Override
	protected void createSrc(JudgeTaskDTO task, File path) throws IOException {
		File src = new File(path, "Main.java");
		FileUtil.write(task.getSource(), src);
	}

	@Override
	protected ExecutorUtil.ExecMessage handlerCompiler(File path) {
		// javac /tmp/OnlineJudgeWorkspace/test/Main.java
		String cmd = compilerCmd.replace("PATH", path.getPath());
		return ExecutorUtil.exec(cmd, 2000);
	}

	@Override
	protected String getRunCommand(File path) {
		//java -classpath /tmp/OnlineJudgeWorkspace/test Main
		return runCmd.replace("PATH", path.getPath());
	}
}
