package com.cheerful.oj.platform.interceptor;

import com.cheerful.oj.common.constant.AuthConstant;
import com.cheerful.oj.platform.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author: Wang Zhifu
 * @create: 2021/11/28 17:49
 * @Description: 已登录的获取用户信息
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

	public static final ThreadLocal<User> loginUser = new ThreadLocal<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String uri = request.getRequestURI();
		//指定拦截路径
		boolean match = new AntPathMatcher().match("/**", uri);
		if (match) {
			return true;
		}
		User user = (User) request.getSession().getAttribute(AuthConstant.LOGIN_USER);
		if (user != null) {
			loginUser.set(user);
			return true;
		} else {
			return false;
		}
	}
}
