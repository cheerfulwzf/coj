package com.cheerful.oj.platform;

import com.cheerful.oj.platform.pojo.vo.LoginAccountVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OjPlatformApplicationTests {

	@Test
	void test() {
		LoginAccountVO loginAccountVo = new LoginAccountVO();
		loginAccountVo.setLoginAccount("13");
	}
}
