package com.test.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	public static String getIP(HttpServletRequest request){
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
