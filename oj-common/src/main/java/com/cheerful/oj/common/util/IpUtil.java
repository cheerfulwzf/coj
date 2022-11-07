package com.cheerful.oj.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
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

	public static String getLocalIp() {
		Enumeration allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			return "";
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					return ip.getHostAddress();
				}
			}
		}
		return "";
	}

	public static boolean isLocalIp(String ip) throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress().equalsIgnoreCase(ip);
	}

	public static String getLocalHost() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException("getLocalHost fail", e);
		}
	}

}
