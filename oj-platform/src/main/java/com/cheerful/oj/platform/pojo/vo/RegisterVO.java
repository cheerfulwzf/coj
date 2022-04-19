package com.cheerful.oj.platform.pojo.vo;

import com.cheerful.oj.platform.exception.Verify;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/28 12:05
 * @DESCRIPTION:
 */
@Data
public class RegisterVO {
    @Verify(name = "用户名",maxLength = 18,minLength = 6)
    private String username;

    @Verify(name = "密码",maxLength = 24,minLength = 6)
    private String password;

    @Verify(name = "邮箱",regular = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
    private String email;

    @Verify(name="验证码",maxLength = 6,minLength = 6)
    private String code;
}
