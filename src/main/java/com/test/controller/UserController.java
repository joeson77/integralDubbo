package com.test.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Integral;
import com.test.entity.UserIP;
import com.test.entity.Users;
import com.test.service.LoginService;
import com.test.service.RegisterService;
import com.test.service.UpdateUserInfoService;
import com.test.util.Config;
import com.test.util.HttpUtil;
import com.test.util.ImageUtil;
import com.test.util.JavaWebToken;
import com.test.util.RedisUtils2;
import com.test.util.StringUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 *                                        ,s555SB@@&                         
 *                                      :9H####@@@@@Xi                       
 *                                     1@@@@@@@@@@@@@@8                      
 *                                   ,8@@@@@@@@@B@@@@@@8                     
 *                                  :B@@@@X3hi8Bs;B@@@@@Ah,                  
 *             ,8i                  r@@@B:     1S ,M@@@@@@#8;                
 *            1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S               
 *            1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5              
 *            ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A              
 *             1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1              
 *              sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1               
 *               .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1                 
 *               s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981              
 *               ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;         
 *                .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS       
 *                s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,     
 *              :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,    
 *            .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8    
 *          :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:   
 *      s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r   
 *   ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i   
 *  5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;   
 *  9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:   
 *   SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5   
 *     ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B   
 *           ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S  
 *              ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#: 
 *            .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
 *
 *
 * @author 厉昀键
 * create in 2018年7月27日
 * 类说明
 *
 */
@Controller
@RequestMapping("/user/*")
@CrossOrigin(allowCredentials="true")
public class UserController {

	@Autowired
	UpdateUserInfoService updateUserInfoService;
	@Autowired
	RegisterService registerService;
	@Autowired
	LoginService loginService;

	protected static Logger log = LoggerFactory.getLogger(UserController.class);
	// 短信平台后缀
	private static String operation = "/industrySMS/sendSMS";
	// 短信平台sid
	private static String accountSid = Config.ACCOUNT_SID;
	// 验证码变量
	int verificationCode;
	//redis连接池
	Jedis jedis = RedisUtils2.getJedis();

	/**
	 * 短信发送请求
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendMsg.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> sendMsg(HttpServletRequest request) {
		// 获取前端用户写入的手机号
		String userTel = request.getParameter("userTel");
		String to = userTel;
		// 短信内容
		String smsContent = "";
		// 发送返回标记
		String flag = "";
		// 获取随机验证码
		verificationCode = (int) (Math.random() * 1000000);
		HttpSession session = request.getSession();
		// 手机号、验证码存入session
		session.setAttribute("verificationCodeSend", verificationCode);
		session.setMaxInactiveInterval(5 * 60);  
		session.setAttribute("userTel", userTel);
		smsContent = "【Forge科技】您的验证码为" + verificationCode + "，请于5分钟内正确输入，如非本人操作，请忽略此短信。";
		String tmpSmsContent = null;
		try {
			tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
		} catch (Exception e) {

		}
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
				+ HttpUtil.createCommonParam();
		// 提交请求
		String result = HttpUtil.post(url, body);
		log.info("result:" + System.lineSeparator() + result);
		JSONObject jsonObj = JSON.parseObject(result);
		String returnCode = (String) jsonObj.get("respCode");
		log.info("发送短信返回状态码是: " + returnCode);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 判断返回码是否为0000
		if (returnCode.equals("00000")) {
			flag = "true";
		} else {
			flag = "false";
		}
		log.info("flag标志是 : " + flag);
		jsonMap.put("flag", flag);
		return jsonMap;
	}

	/**
	 * 提交注册
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "register.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request) {
		Users users = new Users();
		HttpSession session = request.getSession(false);
		// session中获取验证码和手机号
		String verificationCodeStr = request.getParameter("verificationCode");
		int verificationCodeSend = (Integer) session.getAttribute("verificationCodeSend");
		String userTel = (String) session.getAttribute("userTel");
		int verificationCodeInt = Integer.parseInt(verificationCodeStr);
		log.info("获取到的前端验证码是: " + verificationCodeInt);
		log.info("session中获取到的证码是: " + verificationCodeSend);
		String flag = "";
		// 比较用户输入的验证码正确与否
		if (verificationCodeInt == verificationCodeSend) {
			// 截取手机号后6位为默认密码
			String userPwdPlaintext = userTel.substring(userTel.length() - 6, userTel.length());
			String userPwd = DigestUtils.md5Hex(userPwdPlaintext);
			log.info("MD5加密后的字符串为:userPwd =" + userPwd);
			// 存放手机号、密码到user实体类
			users.setUserName("yg_" + userTel.substring(userTel.length() - 6, userTel.length()));
			users.setUserTel(userTel);
			users.setUserPwd(userPwd);
			int returnFlag = registerService.registerUser(users);
			if (returnFlag == 1) {
				log.info("注册受影响的行数为: " + returnFlag);
				flag = "true";
			} else {
				flag = "false";
			}
		} else {
			flag = "false";
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", flag);
		log.info("flag的值是:" + flag);
		return jsonMap;
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,HttpServletResponse response) {
		log.info("进入到login方法");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpSession session = request.getSession();
		String userName = request.getParameter("loginUserName");
		String userPwd = request.getParameter("loginUserPwd");
		String imgCodeClient = request.getParameter("imgCode");
		String imgCodeServer = (String) session.getAttribute("imageCode");
		String IP = StringUtil.getIP(request);
		log.info("登录端的 IP 地址为：" + IP);
		log.info("获取到的 userName 为:" + userName);
		log.info("获取到的 userPwd 为:" + userPwd);
		log.info("获取到的 imgCodeClient 为:" + imgCodeClient);
		log.info("服务器端sessionId为:" + request.getSession().getId());
		log.info("服务器端 imgCodeServer 为:" + imgCodeServer);
		//判断用户输入的验证码是否正确，不区分大小写
		if (imgCodeClient.toUpperCase().equals(imgCodeServer.toUpperCase())) {
			Users users = new Users();
			users.setUserName(userName);
			//用户密码md5加密后存到数据库
			users.setUserPwd(DigestUtils.md5Hex(userPwd));
			Users userSel = loginService.selectUser(users);
			if (userSel == null) {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("errorMsg", "账号或密码输入错误");
				jsonMap.put("errorCode", "1111");
				log.info("errorMsg的值是:账号或密码输入错误");
				return jsonMap;
			}
			userName = userSel.getUserName();
			//查询登录表用户信息
			UserIP userIP = loginService.userIP(userSel.getUserName(), IP);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			String errorMsg = "";
			//用户密码正确且用户状态正常、未登录
			if (userSel != null && userSel.getUserState() == 0 && (userIP == null || userIP.getUserStatue() == 1)) {
				log.info("登录用户的账号状态为:" + userSel.getUserState());
				//判断当前用户IP是否和登录表中的一致
				if (userIP != null && IP.equals(userIP.getUserIP())) {
					log.info("userIP表中该用户不为空...");
					errorMsg = "登陆成功";
				} else if(userIP == null){
					log.info("userIP表中该用户为空...");
					errorMsg = "登陆成功";
					loginService.insertIPForUser(userName, IP, df.format(new Date()));
				} else {
					errorMsg = "登录IP发生变化";
				}
				UserIP userIP2 = new UserIP();
				userIP2.setUserName(userName);
				userIP2.setUserIP(IP);
				userIP2.setUserStatue(0);
				//更改用户登录状态
				int returnFlag = loginService.updateUserOnlineStatue(userIP2);
				log.info("受影响的行数为：" + returnFlag);
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("userName", userSel.getUserName());
				m.put("userPwd", userSel.getUserPwd());
				m.put("userTel", userSel.getUserTel());
				log.info("用户当前积分数为:" + userSel.getUserIntegral());
				m.put("userIntegral", userSel.getUserIntegral());
				m.put("userState", userSel.getUserState());
				m.put("date", df.format(new Date()));
				//用户名、用户密码、手机号、积分、用户状态为载体生成token
				String token = JavaWebToken.createJavaWebToken(m);
				session.setAttribute("token", token);
				//token存到redis
				jedis.set(userSel.getUserName(), token);
				session.setAttribute("userName", userSel.getUserName());
				log.info("生成的token字符串为:" + token);
				log.info("存储到session中的userName 为:" + userSel.getUserName());
				jsonMap.put("errorMsg", errorMsg);
				jsonMap.put("errorCode", "0000");
				jsonMap.put("token", token);
				jsonMap.put("userName", userName);
				log.info("errorMsg的值是:" + errorMsg + ",errorCode码为:0000");
				return jsonMap;
			} else if (userSel != null && userSel.getUserState() == 1){
				log.info("登录用户的账号状态为:" + userSel.getUserState());
				errorMsg = "账号被封号";
				jsonMap.put("errorMsg", errorMsg);
				jsonMap.put("errorCode", "5555");
				log.info("errorMsg的值是:" + errorMsg + ",errorCode码为:5555");
				return jsonMap;
			} else if (userSel != null && userSel.getUserState() == 0 && userIP.getUserStatue() == 0) {
				log.info("登录用户的账号状态为:" + userSel.getUserState());
				errorMsg = "账号已登录";
				jsonMap.put("errorMsg", errorMsg);
				jsonMap.put("errorCode", "6666");
				log.info("errorMsg的值是:" + errorMsg + ",errorCode码为:6666");
				return jsonMap;
			} else {
				errorMsg = "未知错误";
				jsonMap.put("errorMsg", errorMsg);
				jsonMap.put("errorCode", "7777");
				log.info("errorMsg的值是:" + errorMsg);
				return jsonMap;
			}
		} else {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("errorMsg", "验证码输入错误");
			jsonMap.put("errorCode", "3333");
			log.info("errorMsg的值是:" + "验证码输入错误" + ",errorCode码为:3333");
			return jsonMap;
		}
		
	}

	/**
	 * token解析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "lookToken.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String lookToken(HttpServletRequest request) {
		String token = request.getParameter("tokenString");
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		if (userInfoMap == null || userInfoMap.size() == 0) {
			return "token is not true";
		} else {
			log.info("token解析的 userName 为：" + userInfoMap.get("userName"));
			log.info("token解析的 userPwd 为：" + userInfoMap.get("userPwd"));
			return "true";
		}
	}

	/**
	 * 用户信息修改
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> updateUserInfo(HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Users users = new Users();
		String token = request.getParameter("tokenString");
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		String userName = (String) userInfoMap.get("userName");
		String userPwd = request.getParameter("newUserPwd");
		log.info("获取到的 userName:" + userName);
		log.info("获取到的 userPwd:" + userPwd);
		users.setUserName(userName);
		users.setUserPwd(DigestUtils.md5Hex(userPwd));
		int returnFlag = updateUserInfoService.updateUserInfo(users);
		log.info("受影响的行数为:" + returnFlag);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userName", userName);
		m.put("date", df.format(new Date()));
		//用户名载体生成token
		String tokenAgain = JavaWebToken.createJavaWebToken(m);
		//token存到redis
		jedis.set(userName, tokenAgain);
		log.info("用户信息修改新获得token字符串为:" + tokenAgain);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", returnFlag);
		jsonMap.put("errorMsg", "成功");
		jsonMap.put("errorCode", "0000");
		jsonMap.put("token", tokenAgain);
		return jsonMap;
	}

	
	// 生成验证码图片
	@ResponseBody
	@RequestMapping(value = "valicode.do",method = RequestMethod.GET, produces = "application/json;charset=UTF-8") // 对应/user/valicode.do请求
	public void valicode(HttpServletResponse response, HttpSession session) throws Exception {
		// 利用图片工具生成图片
		// 第一个参数是生成的验证码，第二个参数是生成的图片
		Object[] objs = ImageUtil.createImage();
		// 将验证码存入Session
		log.info("获取验证码sessionId为:" + session.getId());
		session.setAttribute("imageCode", objs[0]);
		// 将图片输出给浏览器
		BufferedImage image = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "png", os);
	}
	
	/**
	 * 注销登录
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logout.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> logOut(HttpServletRequest request){
		String userName = request.getParameter("userName");
		UserIP userIP2 = new UserIP();
		userIP2.setUserName(userName);
		userIP2.setUserStatue(1);
		//更改用户登录状态
		int returnFlag = loginService.updateUserOnlineStatue(userIP2);
		if (returnFlag == 1) {
			jedis.del(userName);
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("errorCode", "0000");
			jsonMap.put("errorMsg", "注销成功");
			return jsonMap;
		} else {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Map<String, Object> m = new HashMap<String, Object>();
//			m.put("userName", userName);
//			m.put("date", df.format(new Date()));
//			//用户名载体生成token
//			String tokenAgain = JavaWebToken.createJavaWebToken(m);
//			//token存到redis
//			jedis.set(userName, tokenAgain);
//			log.info("用户信息修改新获得token字符串为:" + tokenAgain);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("errorCode", "1111");
			jsonMap.put("errorMsg", "注销失败");
			return jsonMap;
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "signIn.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> isSignIn(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		log.info("进入到查询是否已签到接口。。。。");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userName = request.getParameter("userName");
		Integral integral = new Integral();
		integral.setUserName(userName);
		integral.setUpdateTime(df.format(new Date()));
		int count = loginService.selectSignInInfo(integral);
		log.info("查询到的签到条数为:" + count);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userName", userName);
		m.put("date", df2.format(new Date()));
		//用户名载体生成token
		String tokenAgain = JavaWebToken.createJavaWebToken(m);
		//token存到redis
		jedis.set(userName, tokenAgain);
		log.info("用户信息修改新获得token字符串为:" + tokenAgain);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (count != 0) {
			jsonMap.put("errorMsg", "已签到");
			jsonMap.put("errorCode", "1111");
			jsonMap.put("token", tokenAgain);
			log.info("用户已签到");
			return jsonMap;
		} else {
			jsonMap.put("errorMsg", "未签到");
			jsonMap.put("errorCode", "0000");
			jsonMap.put("token", tokenAgain);
			log.info("用户未签到");
			return jsonMap;
		}
	}
}
