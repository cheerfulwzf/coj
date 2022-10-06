package com.cheerful.oj.judge;

import com.cheerful.oj.judge.factory.JudgeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OjJudgeApplicationTests {

	@Autowired
	JudgeFactory factory;

	@Test
	public void testSourceType() {
		System.out.println(factory.createJudgeHandler(11));
	}
}
