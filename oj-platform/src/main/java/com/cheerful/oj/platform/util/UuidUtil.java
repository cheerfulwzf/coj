package com.cheerful.oj.platform.util;

import java.util.UUID;

public class UuidUtil {
    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
