package com.cheerful.oj.judge.factory.base;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.util.FileUtil;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/26 15:41
 * @DESCRIPTION:
 */
public abstract class CJudgeHandler extends JudgeHandler {

	@Value("${judge.c.runCmd}")
	private String runCmd;

	@Override
	protected void createSrc(JudgeTaskDTO task, File path) throws IOException {
		File srcFile = new File(path, "main.c");
		FileUtil.write(task.getSource(), srcFile);
	}

	@Override
	protected String getRunCommand(File path) {
		return runCmd.replace("PATH", path.getPath());
	}
}
