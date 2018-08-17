package com.test.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class LoginFilter implements HandlerInterceptor{
	
	protected static Logger log = LoggerFactory.getLogger(LoginFilter.class);

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.info("进入到登录拦截器.....");
		PrintWriter out = null ;
		String userName = request.getParameter("loginUserName");
		String userPwd = request.getParameter("loginUserPwd");
		String imgCodeClient = request.getParameter("imgCode");
		log.info("登录拦截器 loginUserName 为:" + userName);
		log.info("登录拦截器 loginUserPwd 为:" + userPwd);
		log.info("登录拦截器 imgCode 为:" + imgCodeClient);
		if (userName == null || userName.length() == 0 || userPwd == null || userName.length() == 0 || imgCodeClient == null || imgCodeClient.length() == 0) {
			JSONObject res = new JSONObject();
			res.put("errorMsg","未输入验证码");
		    res.put("errorCode","7777");//7777 : 用户名、用户密码、验证码均不能为空
			out = response.getWriter();
		    out.append(res.toString());
			return false;
		} else {
			return true;
		}
	}

}
