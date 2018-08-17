package com.test.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.test.entity.Integral;
import com.test.entity.Users;
import com.test.service.LoginService;
import com.test.service.SelectRecordService;
import com.test.util.JavaWebToken;
import com.test.util.RedisUtils;
import com.test.util.RedisUtils2;

import redis.clients.jedis.Jedis;

/**
 * 
 * 
 * 作者：zhenpeng
 *
 * 创建时间：2018年7月19日 下午2:11:58
 *
 * 功能描述： 积分兑换和查询
 * 
 * 版本： V1.0
 */
@Controller
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
@RequestMapping("/integral/*")
public class IntegralController {

	protected static Logger log = LoggerFactory.getLogger(IntegralController.class);

	Jedis jedis = RedisUtils.getJedis();
	
	Jedis jedis2 = RedisUtils2.getJedis();
	
	@Autowired
	LoginService loginService;
	@Autowired
	SelectRecordService selectRecordService;

	// 测试查询积分
	@ResponseBody
	@RequestMapping(value = "/select.do", method = RequestMethod.POST)
	public Map<String, Object> selectIntegral(ModelAndView view, HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
		Users users = new Users();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取用户的当前积分
		String userName = request.getParameter("userName");
		users.setUserName(userName);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Integer userIntegral = loginService.selectUserIntegral(users).getUserIntegral();
		log.info("查询用户积分测试成功，用户的积分为" + userIntegral);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userName", userName);
		m.put("date", df.format(new Date()));
		//用户名载体生成token
		String tokenAgain = JavaWebToken.createJavaWebToken(m);
		//token存到redis
		jedis2.set(userName, tokenAgain);
		log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
		jsonMap.put("userIntegral", userIntegral);
		jsonMap.put("errorMsg", "sucess");
		jsonMap.put("token", tokenAgain);
		return jsonMap;
	}

	// 测试积分兑换和赚取（赚取和消费积分）
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public Map<String, Object> updateIntegral(ModelAndView view, HttpServletRequest request,HttpSession session) {
		Users users = new Users();
		String username = "";
		if (request.getParameter("userName").equals("admin")) {
			username = request.getParameter("examineUserName");
		} else {
			username = request.getParameter("userName");
		}
		log.info("解析到的 userName值为:" + username);
		users.setUserName(username);
		// 获取用户的当前积分
		Integer userIntegral = loginService.selectUserIntegral(users).getUserIntegral();
		log.info("解析到的积分为:" + userIntegral);
		// 变更的积分数
		String upIntegerStr = request.getParameter("upInteger");
		log.info("获取到的 upIntegerStr 值为:" + upIntegerStr);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int upInteger = 0;
		if (!StringUtils.hasLength(upIntegerStr)) {
			log.info("修改的积分数为空或者是0,修改失败！");
			jsonMap.put("errorMsg", "false");
			return jsonMap;
		}
		upInteger = Integer.parseInt(upIntegerStr);
		// 获取赚取积分和兑换积分渠道
		String channel=request.getParameter("channel");
		// 拼接字符串，存储redis的value值，进行修改用户积分表
		
		if (channel.equals("2")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Integral integral = new Integral();
			integral.setUserName(username);
			integral.setUpdateTime(df.format(new Date()));
			int total = selectRecordService.selectRecordByFlag(integral);
			if (total >= 50) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("date", df.format(new Date()));
				m.put("userName", username);
				//用户名载体生成token
				String tokenAgain = JavaWebToken.createJavaWebToken(m);
				//token存到redis
				jedis2.set(username, tokenAgain);
				log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
				jsonMap.put("token", tokenAgain);
				jsonMap.put("errorMsg", "当天阅读赚取积分数已达到最大值");
				jsonMap.put("errorCode", "3333");
				return jsonMap;
			}
		}
		
		String upKey = "U".concat(username);
		String upVal = "";
		if (jedis.get(upKey) != null) {
			upVal = jedis.get(upKey);
			String[] arrs = upVal.split(",");
			Integer integerFromRedis = Integer.parseInt(arrs[1]);
			upInteger = upInteger + integerFromRedis;
			if ((userIntegral + upInteger) >= 0) {
				upVal = userIntegral + "," + upInteger;
				log.info("拼凑字符串值为upVal:" + upVal);
				jedis.set(upKey, upVal);
				log.info("修改用户积分表key-value存储成功，定时器启动！");
				// 拼接字符串，存储redis的value值，进行添加积分明细表
				String random = UUID.randomUUID().toString();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addKey = "A".concat(random);
				String addVal = username + "," + df.format(new Date()) + "," + upInteger+","+channel;
				log.info("拼凑字符串值为addKey:" + addKey);
				log.info("拼凑字符串值为addvalue:" + addVal);
				jedis.set(addKey, addVal);
				log.info("添加积分明细表key-value存储成功，定时器启动！");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("date", df.format(new Date()));
				m.put("userName", username);
				//用户名载体生成token
				String tokenAgain = JavaWebToken.createJavaWebToken(m);
				//token存到redis
				jedis2.set(username, tokenAgain);
				log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
				jsonMap.put("token", tokenAgain);
				jsonMap.put("errorMsg", "success");
				jsonMap.put("errorCode", "0000");
				return jsonMap;
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("userName", username);
				m.put("date", df.format(new Date()));
				//用户名载体生成token
				String tokenAgain = JavaWebToken.createJavaWebToken(m);
				//token存到redis
				jedis2.set(username, tokenAgain);
				log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
				jsonMap.put("token", tokenAgain);
				jsonMap.put("flag", "false");
				jsonMap.put("errorMsg", "可用积分不足");
				jsonMap.put("errorCode", "1111");
				return jsonMap;
			}
		} else {
			if ((userIntegral + upInteger) >= 0) {
				upVal = userIntegral + "," + upInteger;
				log.info("拼凑字符串值为upVal:" + upVal);
				jedis.set(upKey, upVal);
				log.info("修改用户积分表key-value存储成功，定时器启动！");
				// 拼接字符串，存储redis的value值，进行添加积分明细表
				String random = UUID.randomUUID().toString();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addKey = "A".concat(random);
				String addVal = username + "," + df.format(new Date()) + "," + upInteger+","+channel;
				log.info("拼凑字符串值为addKey:" + addKey);
				log.info("拼凑字符串值为addvalue:" + addVal);
				jedis.set(addKey, addVal);
				log.info("添加积分明细表key-value存储成功，定时器启动！");
				jsonMap.put("errorMsg", "true");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("userName", username);
				m.put("date", df.format(new Date()));
				//用户名载体生成token
				String tokenAgain = JavaWebToken.createJavaWebToken(m);
				//token存到redis
				jedis2.set(username, tokenAgain);
				log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
				jsonMap.put("token", tokenAgain);
				jsonMap.put("errorMsg", "true");
				jsonMap.put("errorCode", "0000");
				return jsonMap;
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("userName", username);
				m.put("date", df.format(new Date()));
				//用户名载体生成token
				String tokenAgain = JavaWebToken.createJavaWebToken(m);
				//token存到redis
				jedis2.set(username, tokenAgain);
				log.info("用户查询积分信息新获得token字符串为:" + tokenAgain);
				jsonMap.put("token", tokenAgain);
				jsonMap.put("errorCode", "1111");
				jsonMap.put("errorMsg", "可用积分不足");
				return jsonMap;
			}
		}
		
	}
}
