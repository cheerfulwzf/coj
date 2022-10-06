package com.cheerful.oj.judge.factory.base;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.util.FileUtil;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/3 21:54
 * @DESCRIPTION:
 */
public abstract class CPPJudgeHandler extends JudgeHandler {

	@Value("${judge.c.runCmd}")
	private String runCmd;

	@Override
	protected void createSrc(JudgeTaskDTO task, File path) throws IOException {
		File src = new File(path, "main.cpp");
		FileUtil.write(task.getSource(), src);
	}

	@Override
	protected String getRunCommand(File path) {
		return runCmd.replace("PATH", path.getPath());
	}
}
