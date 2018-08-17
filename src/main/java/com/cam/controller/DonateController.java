package com.cam.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.math3.complex.Quaternion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.cam.pojo.Donation;
import com.cam.pojo.Users;
import com.cam.query.DonateQueryObject;
import com.cam.query.PageResult;
import com.cam.query.QueryObject;
import com.cam.service.DonateService;
import com.cam.service.UserService;
import com.cam.util.Constant;
import com.cam.util.JavaWebToken;
import com.cam.util.UploadUtil;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月28日 上午11:17:38
 *
 * 功能描述： 捐款申请控制类
 * 
 * 版本： V1.0
 */
@Controller
public class DonateController {
	@Autowired
	DonateService donateService;

	@Autowired
	UserService userService;

	protected static Logger log = LoggerFactory.getLogger(DonateController.class);

	@RequestMapping(value = "/init.do", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpSession session, Model model) {
		String token = request.getParameter("tokenString");
		log.info("进入init.do，获取的token值为：-" + token);
		session.setAttribute("token", token);
		List<Donation> donationList = donateService.indexInit();
		model.addAttribute("donationList", donationList);
		return "redirect:index.do";
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		String token = (String) session.getAttribute("token");
		log.info("进入index.do，获取的token值为：" + token);
		String username = null;
		if (token != null) {
			Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
			if (userInfoMap != null) {
				username = (String) userInfoMap.get("userName");
				model.addAttribute("username", username);
				// 根据用户名调用查询积分接口
				Users user = this.userService.selectUserByUsername(username);
				log.info("当前用户的积分数：" + user.getUserintegral());
				model.addAttribute("integral", user.getUserintegral());
			}
		}
		String type = request.getParameter("eyJhbGciOiJIUzI1NiJ9");
		log.info("传过来的参数type是：" + type);
		List<Donation> donationList = null;
		if (StringUtils.hasLength(type)) {
			donationList = this.donateService.selectDonateByType(type);
		} else {
			donationList = donateService.indexInit();
		}
		if (donationList != null) {
			log.info("获得的集合元素个数是：" + donationList.size());
		}
		model.addAttribute("type", type);
		model.addAttribute("donationList", donationList);
		return "/index";
	}

	@RequestMapping(value = "/doDonate.do", method = RequestMethod.GET)
	public String doDonate(HttpServletRequest request, HttpSession session, Model model) {
		log.info("执行doDonate.do...");
		String token = (String) session.getAttribute("token");
		if (token == null) {
			// 返回登录页面
			return "/toLogin";
		}
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		if (userInfoMap == null) {
			// 返回登录页面
			return "/toLogin";
		}
		// 获取用户名
		String username = (String) userInfoMap.get("userName");
		model.addAttribute("username", username);
		// 根据用户名调用查询积分接口
		Users user = this.userService.selectUserByUsername(username);
		log.info("当前用户的积分数：" + user.getUserintegral());
		model.addAttribute("integral", user.getUserintegral());
		return "/doDonate";
	}

	@RequestMapping(value = "/publishDonateSubmit.do", method = RequestMethod.POST)
	public String publishDonateSubmit(HttpServletRequest request, HttpSession session, MultipartFile myfile,
			Donation donate) throws Exception {
		String token = (String) session.getAttribute("token");
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		// 获取用户名
		String username = null;
		if (userInfoMap != null)
			username = (String) userInfoMap.get("userName");
		if (StringUtils.hasLength(username)) {
			donate.setUsername(username);
		} else {
			// 返回登录页面
			return "/toLogin";
		}
		log.info("传过来的username属性值为:" + username);
		// 获取图片的url
		Map<String, Object> map = uploadFile(session, myfile);
		donate.setImgUrl((String) map.get("imgUrl"));
		// 设置申请时间
		donate.setApplyTime(new Date());
		// 给捐款申请设定状态 0：待审核状态
		donate.setStatus(Constant.DONATE_STATE_UNCREDIT);
		// 设定详细地址
		StringBuilder address = new StringBuilder();
		address.append(request.getParameter("province")).append(request.getParameter("city"))
				.append(request.getParameter("district"));
		donate.setAddress(address.append(donate.getAddress()).toString());
		// 给捐款申请设定获取积分
		int score = getIntegral(donate.getType(), donate.getNumber());
		donate.setEx1("" + score);
		int ret = this.donateService.insert(donate);
		if (ret > 0) {
			log.info("测试申请捐赠接口成功，更新数据数：" + ret);
			return "redirect:selectDonate.do";
		} else {
			log.info("测试申请捐赠接口异常！");
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
	public Map<String, Object> uploadFile(HttpSession session, MultipartFile myfile)
			throws IllegalStateException, IOException {
		String oldFileName = "";
		if (myfile != null) {
			oldFileName = myfile.getOriginalFilename();
		}
		String file_path = session.getServletContext().getRealPath("/upload");
		if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
			String newFileName = UploadUtil.upload(myfile, file_path);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imgUrl", newFileName);
			log.info("测试上传图片接口成功！");
			return map;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			log.info("测试上传图片接口异常！");
			return map;
		}
	}

	@RequestMapping(value = "/selectDonate.do", method = RequestMethod.GET)
	public String selectDonate(HttpSession session, Model model, DonateQueryObject qo) {
		// 获取用户名
		String token = (String) session.getAttribute("token");
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		String username = null;
		if (userInfoMap != null)
			username = (String) userInfoMap.get("userName");
		if (StringUtils.hasLength(username)) {
			qo.setUsername(username);
		} else {
			// 返回登录页面
			return "/toLogin";
		}
		log.info("查询的qo对象属性值：" + qo.toString());
		PageResult result = this.donateService.query(qo);
		log.info("遍历得到的集合元素为：" + result.getTotalCount());
		// 根据用户名调用查询积分接口
		Users user = this.userService.selectUserByUsername(username);
		log.info("当前用户的积分数：" + user.getUserintegral());
		model.addAttribute("integral", user.getUserintegral());
		model.addAttribute("Result", result);
		model.addAttribute("username", username);
		return "donate_list";
	}

	@RequestMapping(value = "/query.do", method = RequestMethod.POST)
	public String queryDonate(HttpSession session, Model model, @ModelAttribute("qo") DonateQueryObject qo) {
		String token = (String) session.getAttribute("token");
		Map<String, Object> userInfoMap = JavaWebToken.parserJavaWebToken(token);
		// 获取用户名
		String username = null;
		if (userInfoMap != null)
			username = (String) userInfoMap.get("userName");
		if (StringUtils.hasLength(username)) {
			qo.setUsername(username);
		} else {
			// 返回登录页面
			return "/toLogin";
		}
		log.info("查询的qo对象属性值：" + qo.toString());
		PageResult result = this.donateService.query(qo);
		log.info("遍历得到的集合元素为：" + result.getTotalCount());
		// 根据用户名调用查询积分接口
		Users user = this.userService.selectUserByUsername(username);
		log.info("当前用户的积分数：" + user.getUserintegral());
		model.addAttribute("integral", user.getUserintegral());
		model.addAttribute("Result", result);
		model.addAttribute("username", username);
		return "donate_list";
	}

	@ResponseBody
	@RequestMapping(value = "/getScore.do", method = RequestMethod.POST)
	public Map<String, Object> getScore(HttpServletRequest request) {
		String numberStr = request.getParameter("number");
		String type = request.getParameter("type");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int number = 0;
		if (StringUtils.hasLength(numberStr)) {
			number = Integer.valueOf(numberStr);
		}
		if (number < 5) {
			jsonMap.put("errMsg", "选择的捐献数量不能少于5！");
			jsonMap.put("flag", "error");
			return jsonMap;
		}
		log.info("从前端传来的number和type值为：" + number + "," + type);
		jsonMap.put("score", getIntegral(type, number));
		jsonMap.put("flag", "success");
		return jsonMap;
	}

	private Integer getIntegral(String type, int number) {
		switch (type) {
		case Constant.DONATE_TYPE_CLOTHES:
			return Constant.DONATE_TYPE_CLOTHES_SCORE * number;
		case Constant.DONATE_TYPE_BOOK:
			return Constant.DONATE_TYPE_BOOK_SCORE * number;
		case Constant.DONATE_TYPE_OTHER:
			return Constant.DONATE_TYPE_OTHER_SCORE * number;
		default:
			return -1;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/queryAjax.do", method = RequestMethod.POST)
	public Map<String, Object> queryAjax(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		log.info("传过来的参数type是：" + type);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		DonateQueryObject qo = new DonateQueryObject();
		qo.setType(type);
		try {
			PageResult result = this.donateService.query(qo);
			model.addAttribute("donationList", result.getResult());
			jsonMap.put("donationList", result.getResult());
			jsonMap.put("Msg", "success");
		} catch (Exception ex) {
			jsonMap.put("errorMsg", ex.getMessage());
		}
		return jsonMap;
	}
}
