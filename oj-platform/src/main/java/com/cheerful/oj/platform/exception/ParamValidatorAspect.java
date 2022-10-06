package com.cheerful.oj.platform.exception;

import java.lang.reflect.Field;
import java.util.regex.Pattern;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.ObjectUtils;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/31 10:32
 * @DESCRIPTION: AOP对请求的参数进行拦截 {@link com.cheerful.oj.common.exception.ParamException}
 * {@link AssertException} {@link ServerExceptionResolver}
 */
public class ParamValidatorAspect {

	@Pointcut("execution(* com.cheerful.oj.platform.controller..*(..))")
	public void validatorPointcut() {
	}

	@Before("validatorPointcut()")
	public void parameterVerify(JoinPoint point) throws Exception {
		for (Object arg : point.getArgs()) {
			Class<?> clazz = arg.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Verify.class)) {
					//获取注解对象
					Verify verify = field.getAnnotation(Verify.class);
					//取出注解的属性
					String name = verify.name();
					int maxLength = verify.maxLength();
					int minLength = verify.minLength();
					boolean required = verify.required();
					boolean notNull = verify.notNull();
					String regular = verify.regular();
					//属性值
					Object fieldObj = field.get(arg);
					//是否时必传 断言判断
					if (required) {
						AssertException.assertMethod(fieldObj != null, String.format("【%s】为必传参数", name));
					}
					//字符串的 非空校验
					if (notNull) {
						AssertException.assertMethod(!ObjectUtils.isEmpty(fieldObj),
							String.format("【%s】不能为空", name));
					}
					//是否有最大长度限制 断言判断
					if (Integer.MAX_VALUE != maxLength) {
						AssertException.assertMethod(maxLength > String.valueOf(fieldObj).length(),
							String.format("【%s】长度不合理，最大长度为【%s】", name, maxLength));
					}
					//是否有最小长度限制 断言判断
					if (Integer.MIN_VALUE != minLength) {
						AssertException.assertMethod(minLength < String.valueOf(fieldObj).length(),
							String.format("【%s】长度不合理，最小长度为【%s】", name, minLength));
					}
					//是否有正则校验
					if (!"".equals(regular)) {
						Pattern pattern = Pattern.compile(regular);
						//断言判断正则
						AssertException.assertMethod(pattern.matcher(String.valueOf(fieldObj)).matches(),
							String.format("参数【%s】不符合规则", name));
					}
				}
			}
		}
	}

}
