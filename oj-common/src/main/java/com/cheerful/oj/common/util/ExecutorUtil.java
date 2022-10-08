package com.cheerful.oj.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import lombok.Data;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 20:54
 * @DESCRIPTION: 编译、运行响应的命令
 */
public class ExecutorUtil {

	/**
	 * 开始执行命令
	 *
	 * @param cmd          命令 eg: <b>javac /tmp/OnlineJudgeWorkspace/test/Main.java</b>
	 * @param milliseconds 等待时间
	 * @return {@link ExecMessage} 返回执行命令后的输出流
	 */
	public static ExecMessage exec(String cmd, long milliseconds) {
		Runtime runtime = Runtime.getRuntime();
		final Process exec;
		try {
			exec = runtime.exec(cmd);
			if (!exec.waitFor(milliseconds, TimeUnit.MILLISECONDS)) {
				if (exec.isAlive()) {
					exec.destroy();
				}
				throw new InterruptedException();
			}
		} catch (IOException e) {
			return new ExecMessage(e.getMessage(), null);
		} catch (InterruptedException e) {
			return new ExecMessage("timeOut", null);
		}
		ExecMessage execMessage = new ExecMessage();
		execMessage.setError(getForResultStream(exec.getErrorStream()));
		execMessage.setStdout(getForResultStream(exec.getInputStream()));
		return execMessage;
	}

	/**
	 * 从执行任务的流返回结果信息
	 *
	 * @param stream 信息流
	 * @return 结果信息
	 */
	private static String getForResultStream(InputStream stream) {
		ByteArrayOutputStream buffer = null;
		try {
			buffer = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int n;
			while ((n = stream.read(bytes)) != -1) {
				buffer.write(bytes, 0, n);
			}
			String res = buffer.toString("UTF-8").trim();
			return res.equals("") ? null : res;
		} catch (IOException e) {
			return e.getMessage();
		} finally {
			try {
				stream.close();
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Data
	public static class ExecMessage {

		private String error;
		private String stdout;

		public ExecMessage(String error, String stdout) {
			this.error = error;
			this.stdout = stdout;
		}

		public ExecMessage() {
		}
	}

}
