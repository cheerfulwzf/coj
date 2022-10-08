package com.cheerful.oj.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;

/**
 * @Copyright:www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date:2021/6/22
 * @Author:feixinyong
 */
public class JsonUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 解决bigdecimal反序列化后小数点精度丢失问题
		mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
		// 禁止科学计数法转换
		mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	public static String toJsonString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String content, Class<T> clazz) {
		try {
			return mapper.readValue(content, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String content, TypeReference<T> typeReference) {
		try {
			return mapper.readValue(content, typeReference);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> fromArray(String content, Class<T> clazz) {
		try {
			return mapper.readValue(content, new TypeReference<List<T>>() {
			});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
