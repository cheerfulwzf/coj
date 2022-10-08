package com.cheerful.oj.common.util;

import java.util.UUID;

public class UuidUtil {

	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
