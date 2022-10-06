package com.cheerful.oj.judge.factory;

import com.cheerful.oj.judge.factory.base.JudgeHandler;
import com.cheerful.oj.judge.factory.impl.GoJudgeHandler;
import com.cheerful.oj.judge.factory.impl.JSJudgeHandler;
import com.cheerful.oj.judge.factory.impl.JavaJudgeHandler;
import com.cheerful.oj.judge.factory.impl.OtherJudgeHandler;
import com.cheerful.oj.judge.factory.impl.Python2JudgeHandler;
import com.cheerful.oj.judge.factory.impl.Python3JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP11JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP14JudgeHandler;
import com.cheerful.oj.judge.factory.impl.cpp.GNUCPP17JudgeHandler;
import com.cheerful.oj.judge.factory.impl.gcc.GNUC90JudgeHandler;
import com.cheerful.oj.judge.factory.impl.gcc.GNUC99JudgeHandler;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 13:59
 * @DESCRIPTION:
 */
@Component
public class JudgeFactory {

	private final JavaJudgeHandler java;

	private final GNUC90JudgeHandler c90;

	private final GNUC99JudgeHandler c99;

	private final GNUCPP11JudgeHandler cpp11;

	private final GNUCPP14JudgeHandler cpp14;

	private final GNUCPP17JudgeHandler cpp17;

	private final GoJudgeHandler go;

	private final JSJudgeHandler js;

	private final Python2JudgeHandler py2;

	private final Python3JudgeHandler py3;

	private final OtherJudgeHandler other;

	@Autowired
	public JudgeFactory(OtherJudgeHandler other, JavaJudgeHandler java, GNUC90JudgeHandler c90,
		GNUC99JudgeHandler c99, GNUCPP11JudgeHandler cpp11, GNUCPP14JudgeHandler cpp14,
		GNUCPP17JudgeHandler cpp17, GoJudgeHandler go, JSJudgeHandler js, Python2JudgeHandler py2,
		Python3JudgeHandler py3) {
		this.java = java;
		this.c90 = c90;
		this.c99 = c99;
		this.cpp11 = cpp11;
		this.cpp14 = cpp14;
		this.cpp17 = cpp17;
		this.go = go;
		this.js = js;
		this.py2 = py2;
		this.py3 = py3;
		this.other = other;
	}

	/**
	 * 创建响应的语言的模板
	 *
	 * @param orderType 选择哪种语言？ 0--->Java
	 * @return 最终各语言的判题
	 */
	public JudgeHandler createJudgeHandler(Integer orderType) {
		switch (orderType) {
			case 0:
				return java;
			case 1:
				return c90;
			case 2:
				return c99;
			case 3:
				return cpp11;
			case 4:
				return cpp14;
			case 5:
				return cpp17;
			case 6:
				return go;
			case 8:
				return py2;
			case 9:
				return py3;
			case 10:
				return js;
			default:
				other.init(orderType.toString());
				return other;
		}
	}

	public List<JudgeTypeVO> listAllJudgeType() {
		return null;
	}

	@Data
	public static class JudgeTypeVO {
		private Integer code;
		private String name;
		private String compileCmd;
		private String runCmd;
	}
}
