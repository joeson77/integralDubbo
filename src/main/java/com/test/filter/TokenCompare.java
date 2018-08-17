package com.test.filter;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.test.util.JavaWebToken;
import com.test.util.RedisUtils2;

import redis.clients.jedis.Jedis;

/**
 * Servlet Filter implementation class TokenCompare
 */
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class TokenCompare implements HandlerInterceptor {
	
	protected static Logger log = LoggerFactory.getLogger(TokenCompare.class);

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Jedis jedis = RedisUtils2.getJedis();
		log.info("拦截器开始运行");
		String tokenClient = request.getParameter("tokenString");
		String userName = request.getParameter("userName");
		log.info("client传递的token值为:" + tokenClient);
		log.info("client传递的userName值为:" + userName);
		PrintWriter out = null ;
		String tokenServer = "";
		if (tokenClient != null && tokenClient.length() != 0 && request.getParameter("userName") != null) {
			log.info("进入到非空判断。。。。");
			Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(tokenClient);
			String userNmae = (String) userInfoMap.get("userName");
			tokenServer = jedis.get(userNmae);
		} else {
			log.info("进入到空判断。。。。");
			JSONObject res = new JSONObject();
			res.put("errorMsg","未登录");
		    res.put("errorCode","2222");
			out = response.getWriter();
		    out.append(res.toString());
			return false;
		}
		log.info("redis中获取到的token值为:" + tokenServer);
		if (tokenServer != null && tokenClient != null) {
			if (tokenServer.equals(tokenClient)) {
				return true;
			} else {
				JSONObject res = new JSONObject();
				res.put("errorMsg","token错误");
			    res.put("errorCode","11111");
				out = response.getWriter();
			    out.append(res.toString());
				return false;
			}
		} else {
			JSONObject res = new JSONObject();
			res.put("errorMsg","token验证失败");
		    res.put("errorCode","22222");
			out = response.getWriter();
		    out.append(res.toString());
			return false;
		}
		
	}
	
}
