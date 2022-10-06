package com.cheerful.oj.judge.factory.impl;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.exception.ParamException;
import com.cheerful.oj.judge.exception.OrderTypeNotFoundException;
import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.util.ExecutorUtil;
import com.cheerful.oj.judge.util.ExecutorUtil.ExecMessage;
import com.cheerful.oj.judge.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Copyright: www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date: 8/18/22 7:19 PM
 * @Author: wangzhifu
 */
@ToString
@Component
@Configuration
@ConfigurationProperties(prefix = "custom-cmd")
public class OtherJudgeHandler extends JudgeHandler {

	private Map<String, List<String>> map;

	private String compilerCmd;

	private String runCmd;

	public void init(String orderType) {
		if (!map.containsKey(orderType)) {
			throw new OrderTypeNotFoundException("not support your source code type");
		}
		List<String> cmds = map.get(orderType);
		if (cmds.size() == 2) {
			//有的语言不用编译，则只有一条命令，如js
			this.runCmd = cmds.get(1);
		} else if (cmds.size() == 3) {
			this.compilerCmd = cmds.get(1);
			this.runCmd = cmds.get(2);
		} else {
			throw new ParamException();
		}
	}

	@Override
	protected void createSrc(JudgeTaskDTO task, File path) throws IOException {
		if (StringUtils.isEmpty(compilerCmd)) {
			return;
		}
		File src = new File(path, compilerCmd.substring(compilerCmd.indexOf('/')));
		FileUtil.write(task.getSource(), src);
	}

	@Override
	protected ExecMessage handlerCompiler(File path) {
		if (StringUtils.isEmpty(compilerCmd)) {
			return null;
		}
		String cmd = compilerCmd.replace("PATH", path.getPath());
		return ExecutorUtil.exec(cmd, 2000);
	}

	@Override
	protected String getRunCommand(File path) {
		return runCmd.replace("PATH", path.getPath());
	}

	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}

	public void setCompilerCmd(String compilerCmd) {
		this.compilerCmd = compilerCmd;
	}

	public void setRunCmd(String runCmd) {
		this.runCmd = runCmd;
	}

	public Map<String, List<String>> getMap() {
		return map;
	}
}
