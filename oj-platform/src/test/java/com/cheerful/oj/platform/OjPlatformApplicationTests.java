package com.cheerful.oj.platform;

import com.cheerful.oj.platform.pojo.vo.LoginAccountVo;
import com.cheerful.oj.platform.util.IpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OjPlatformApplicationTests {

    @Test
    void test(){
        LoginAccountVo loginAccountVo = new LoginAccountVo();
        loginAccountVo.setLoginAccount("13");
    }
}
