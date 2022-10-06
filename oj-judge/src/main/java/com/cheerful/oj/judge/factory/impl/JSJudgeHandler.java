package com.cheerful.oj.judge.factory.impl;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import com.cheerful.oj.judge.util.FileUtil;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/4/3 22:02
 * @DESCRIPTION:
 */
@Slf4j
@Service
public class JSJudgeHandler extends JudgeHandler {

	@Value("${judge.js.runCmd}")
	private String runWord;

	@Override
	protected void createSrc(JudgeTaskDTO task, File path) throws IOException {
		File src = new File(path, "main.js");
		FileUtil.write(task.getSource(), src);
	}

	@Override
	protected ExecutorUtil.ExecMessage handlerCompiler(File path) {
		log.info("JavaScript no Compiler");
		return null;
	}

	@Override
	protected String getRunCommand(File path) {
		return runWord.replace("PATH", path.getPath());
	}
}
