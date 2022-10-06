package com.cheerful.oj.platform.util;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/30 21:42
 * @DESCRIPTION:
 */
@Component
public class MailUtil {

	private final static String from = "cheerful0120@qq.com";
	private final static String subject = "cheerful OJ验证码";
	private final static String content = "欢迎你使用cheerful OJ系统，本次验证码10分钟内有效，你的本次验证码是：";
	@Autowired
	JavaMailSender javaMailSender;

	public void sendCode(Integer code, String sendTo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(sendTo);
		message.setSentDate(new Date());
		message.setText(content + code);
		message.setSubject(subject);
		javaMailSender.send(message);
	}
}
