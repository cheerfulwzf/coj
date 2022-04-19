package com.cheerful.oj.platform.util;

import com.cheerful.oj.common.constant.AuthConstant;
import com.cheerful.oj.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/30 21:42
 * @DESCRIPTION:
 */
@Component
public class MailUtil {
    @Autowired
    JavaMailSender javaMailSender;

    private final static String from="cheerful0120@qq.com";
    private final static String subject="cheerful OJ验证码";
    private final static String content="欢迎你使用cheerful OJ系统，本次验证码10分钟内有效，你的本次验证码是：";

    public void sendCode(Integer code, String sendTo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(sendTo);
        message.setSentDate(new Date());
        message.setText(content+code);
        message.setSubject(subject);
        javaMailSender.send(message);
    }
}
