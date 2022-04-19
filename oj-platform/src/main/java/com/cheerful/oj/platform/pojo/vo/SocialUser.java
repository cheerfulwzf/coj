package com.cheerful.oj.platform.pojo.vo;

import lombok.Data;

/**
 * @AUTHOR: : Wang Zhifu
 * @DATE: : 2021/11/16 11:31
 * @DESCRIPTION: : 社交登录（gitee）返回的实体类
 */
@Data
public class SocialUser {
    private String access_token;

    private String token_type;

    private String expires_in;

    private String refresh_token;

    private String scope;

    private String created_at;
}
