package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.entity.PublicBenefit;
import com.test.entity.Users;
import com.test.service.PublicBenefitService;
import com.test.util.JavaWebToken;
import com.test.util.RedisUtils2;

import redis.clients.jedis.Jedis;

@Controller
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class PublicBenefitController {
	
	@Autowired
	PublicBenefitService publicBenefitService;
	
	protected static Logger log = LoggerFactory.getLogger(PublicBenefitController.class);
	
	Jedis jedis = RedisUtils2.getJedis();
	
	/**
	 * 打开首页
	 */
	@RequestMapping("index.do")
	public String index(HttpServletRequest request,Model model){
		log.info("进入主页方法......");
		if (request.getParameter("tokenString") != null && !request.getParameter("tokenString").equals("null")) {
			String receiveUserName = request.getParameter("userName");
			Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(request.getParameter("tokenString"));
			String userName = (String) userInfoMap.get("userName");
			if (receiveUserName.equals(userName)) {
				log.info("token解析的 userName 为：" + userName);
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				log.info("该账号的sessionid为: " + request.getSession().getId());
				publicBenefitService.updateSessionID(userName, request.getSession().getId());
				String token =  jedis.get(userName);
				if (token != null) {
					log.info("redis中获取到的token值为：" + token);
					Users user = publicBenefitService.selectUserInfo(userName);
					session.setAttribute("token", token);
					session.setAttribute("user", user);
					model.addAttribute("user", user);
					model.addAttribute("token",token);
				}else {
					log.info("redis中token为空");
					model.addAttribute("userName", null);
					model.addAttribute("token", null);
				}
			}else {
				log.info("用户信息异常...");
				model.addAttribute("userName", null);
				model.addAttribute("token", null);
			}
		}else {
			log.info("用户未登录....");
			model.addAttribute("userName", null);
			model.addAttribute("token", null);
		}
		return "index";
	}
	
	/**
	 * 我们的行动
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("weDo.do")
	public String weDo(HttpServletRequest request,Model model){
		log.info("进入到公益活动展示列表......");
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		if (userName != null) {
			String token =  jedis.get(userName);
			Users user = publicBenefitService.selectUserInfo(userName);
			session.setAttribute("user", user);
			session.setAttribute("token", token);
			log.info("woDo方法中sessionID:" + session.getId());
		} 
		List<PublicBenefit> listActive = publicBenefitService.selectAllActive();
		model.addAttribute("listActive", listActive);
		return "weDo";
	}
	
	/**
	 * 公益列表展示
	 * @param request
	 * @return
	 */
	@RequestMapping("getDetails.do")
	@ResponseBody
	public Map < String , Object > getDetails(HttpServletRequest request){
		log.info("正在查询公益活动详情......");
		int activeId=Integer.parseInt(request.getParameter("activeId"));
		PublicBenefit publicBenefit = publicBenefitService.selectById(activeId);
		Map < String , Object > jsonMap = new HashMap< String , Object>();
		jsonMap.put("publicBenefit", publicBenefit);
		return jsonMap;
	}
	
	/**
	 * 更新已有积分
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateHaving.do")
	public Map < String , Object > updateHaving(HttpServletRequest request){
		log.info("进入到更新已有积分方法中。。。。");
		int num = Integer.parseInt(request.getParameter("num"));
		int displayActiveId = Integer.parseInt(request.getParameter("displayActiveId"));
		int activeStatue = publicBenefitService.selectActiveStatue(displayActiveId);
		Map < String , Object > jsonMap = new HashMap< String , Object>();
		//程序休眠一秒
		try {
			Thread.sleep(1000);
			log.info("程序休眠一秒结束");
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		if (activeStatue == 3) {
			jsonMap.put("errorMsg", "活动已完成");
			jsonMap.put("errorCode", "3333");
			return jsonMap;
		}
		log.info("获取到的积分为:" + num);
		log.info("获取到的id为:" + displayActiveId);
		int returnFlag = publicBenefitService.updateHaving(displayActiveId, num);
		if (returnFlag == 1) {
			jsonMap.put("flag", "true");
			jsonMap.put("errorCode", "0000");
			return jsonMap;
		} else {
			jsonMap.put("flag", "false");
			jsonMap.put("errorCode", "1111");
			return jsonMap;
		}
	}
}
