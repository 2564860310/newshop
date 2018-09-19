package com.cyhz.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {

	public static boolean checkVenifyCode(HttpServletRequest request) {
		String verifyCodeExcepted=(String)request.getSession().
				getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual=HttpServletRequestUtil.getString(request, "verifyCodeActual");
		//需要在spring-web.xml中配置文件上传拦截器，要不拿不到值一直为null
		if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExcepted)) {
			return false;
		}
		return true;
	}
	
}
