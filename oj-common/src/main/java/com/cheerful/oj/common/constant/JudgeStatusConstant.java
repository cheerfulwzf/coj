package com.cheerful.oj.common.constant;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:47
 * @DESCRIPTION: 判题顺利完成，返回判题后提示结果和状态
 */
public enum JudgeStatusConstant {
	/**
	 * 反馈的状态码和信息，状态码越大说明问题越大
	 */
	BLOCK(-1, "Pending"),
	AC(0, "Accept"),
	PE(1, "Presentation Error"),
	TLE(2, "Time Limit Exceeded"),
	MLE(3, "Memory Limit Exceeded"),
	WA(4, "Wrong Answer"),
	RE(5, "Runtime Error"),
	OLE(6, "Output Limit Exceeded"),
	CE(7, "Compile Error"),
	SE(8, "System Error");

	private final Integer code;
	private final String msg;

	JudgeStatusConstant(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getMsgByCode(Integer code) {
		JudgeStatusConstant[] constants = values();
		for (JudgeStatusConstant constant : constants) {
			if (constant.getCode().equals(code)) {
				return constant.getMsg();
			}
		}
		return null;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}


}
