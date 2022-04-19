package com.cheerful.oj.platform.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 23:33
 * @DESCRIPTION:
 */
public class IpUtil {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null) {
            if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(",");
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null) {
            if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
