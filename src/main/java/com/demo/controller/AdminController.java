package com.demo.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Donation;
import com.demo.entity.Integral;
import com.demo.entity.PublicBenefit;
import com.demo.entity.Users;
import com.demo.service.UpdateUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sun.misc.BASE64Decoder;

/**
 *
 * @author 厉昀键
 * create in 2018年8月9日
 * 类说明
 *
 */
@Controller
public class AdminController {
	
	@Autowired
	UpdateUserService updateUserService;
	
	Users users = new Users();
	
	private Logger logger = Logger.getLogger(AdminController.class);
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("index")
	public String login(){
		return "login";
	}
	
	/**
	 * 打开后台管理系统首页
	 * @return
	 */
	@RequestMapping("indexIntegral")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 打开用户后台管理首页
	 * @param model
	 * @return
	 */
	@RequestMapping("adminIndexUser")
	public String showAllUses(Model model,HttpServletRequest request){
		int page;
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} else {
			page = 1;
		}
		PageHelper.startPage(page,2);
		List<Users> userList = updateUserService.selectAllUsers();
	    PageInfo<Users> p=new PageInfo<Users>(userList);
		model.addAttribute("userList", userList);
		model.addAttribute("pagehelper",p);
		return "indexUser";
	}
	
	/**
	 * 更改用户账号状态
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserState")
	public boolean updateUserState(HttpServletRequest request){
		String userIdStr = request.getParameter("userId");
		String updateUserStateStr = request.getParameter("updateUserState");
		int userIdInt = Integer.parseInt(userIdStr);
		int updateUserStateInt = Integer.parseInt(updateUserStateStr);
		logger.info("获取到的 userId = " + userIdInt);
		logger.info("获取到的 updateUserState = " + updateUserStateInt);
		users.setId(userIdInt);
		users.setUserState(updateUserStateInt);
		int flag = updateUserService.updateUserState(users);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据主键查询用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectUserInfoById")
	public Users selectUserInfoById(HttpServletRequest request){
		String idStr = request.getParameter("userId");
		int idInt = Integer.parseInt(idStr);
		Users userInfo = updateUserService.selectUserInfoById(idInt);
		return userInfo;
	}
	
	/**
	 * 主键修改用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserInfo")
	public boolean updateUserInfo(HttpServletRequest request){
		String userIdStr = request.getParameter("retUserId");
		String userName = request.getParameter("retUserName");
		String userPwd = request.getParameter("retUserPwd");
		String userTel = request.getParameter("retUserTel");
		String userIntegralStr = request.getParameter("retUserIntegral");
		int userIdInt = Integer.parseInt(userIdStr);
		int userIntegralInt = Integer.parseInt(userIntegralStr);
		logger.info("获取到的 userIdInt = " + userIdInt);
		logger.info("获取到的 userName = " + userName);
		logger.info("获取到的 userPwd = " + userPwd);
		logger.info("获取到的 userTel = " + userTel);
		logger.info("获取到的 userIntegralInt = " + userIntegralInt);
		users.setId(userIdInt);
		users.setUserName(userName);
		users.setUserPwd(userPwd);
		users.setUserTel(userTel);
		users.setUserIntegral(userIntegralInt);
		int returnFlag = updateUserService.updateUserInfo(users);
		if (returnFlag == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据主键删除用户
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteUserById")
	public boolean deleteUserById(HttpServletRequest request){
		String userIdStr = request.getParameter("userId");
		int userIdInt = Integer.parseInt(userIdStr);
		logger.info("获取到的 userIdInt = " + userIdInt);
		int returnFlag = updateUserService.deleteUserById(userIdInt);
		if (returnFlag == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 积分明细管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("adminIndexRecord")
	public String showAllRecord(Model model,HttpServletRequest request){
		Integral integral = new Integral();
		String userName = request.getParameter("userNameInput");
		String updateTime = request.getParameter("updateTimeInput");
		logger.info("前端传递的 userName = " + userName);
		logger.info("前端传递的 updateTime = " + updateTime);
		if (userName != null && userName.length() != 0 && updateTime.length() == 0) {
			logger.info("这里是userName不为空，updateTime为空的条件！");
			integral.setUserName(userName);
		} else if(updateTime != null && updateTime.length() != 0 && userName.length() == 0) {
			logger.info("这里是updateTime不为空，userName为空的条件！");
			integral.setUpdateTime(updateTime);
		} else if (userName != null && userName.length() !=0 && updateTime.length() !=0 && updateTime != null) {
			logger.info("这里是updateTime不为空，userName不为空的条件！");
			integral.setUserName(userName);
			integral.setUpdateTime(updateTime);
		}
		int page;
		String prePage = request.getParameter("pageNum");
		logger.info("获取到的当前页 prePage 为:" + prePage);
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(prePage);
		} else {
			page = 1;
		}
		PageHelper.startPage(page,8);
		List<Integral> recordList = updateUserService.selectAllRecord(integral);
	    PageInfo<Integral> p=new PageInfo<Integral>(recordList);
		model.addAttribute("recordList", recordList);
		model.addAttribute("pagehelper",p);
		model.addAttribute("userNameReturn",userName);
		model.addAttribute("updateTimeReturn",updateTime);
		return "indexRecord";
	}
	
	/**
	 * 积分公益管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("adminIndexPublicBenefit")
	public String indexBenefit(HttpServletRequest request,Model model){
		int page;
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} else {
			page = 1;
		}
		PageHelper.startPage(page,3);
		List<PublicBenefit> activeList = updateUserService.selectAllActive();
		PageInfo<PublicBenefit> p=new PageInfo<PublicBenefit>(activeList);
		model.addAttribute("activeList", activeList);
		model.addAttribute("pagehelper",p);
		return "indexPublic";
	}
	
	/**
	 * 关闭公益
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("shutDownActive")
	public String shutDownActive(HttpServletRequest request,Model model){
		String activeIdStr = request.getParameter("activeId");
		logger.info("获取到的 activeId 为:" + activeIdStr);
		int activeId = Integer.parseInt(activeIdStr);
		int returnFlag = updateUserService.shutDownActive(activeId);
		if (returnFlag == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 开启公益
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("startActive")
	public String startActive(HttpServletRequest request,Model model){
		String activeIdStr = request.getParameter("activeId");
		logger.info("获取到的 activeId 为:" + activeIdStr);
		int activeId = Integer.parseInt(activeIdStr);
		int returnFlag = updateUserService.startActive(activeId);
		if (returnFlag == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 创建公益
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createActive")
	public String createActive(HttpServletRequest request,Model model){
		PublicBenefit publicBenefit = new PublicBenefit();
		String activeName = request.getParameter("activeName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String activeType = request.getParameter("activeType");
		String activeNeedIntegral = request.getParameter("activeNeedIntegral");
		String activeImg = request.getParameter("activeImg");
		String activeDescribe = request.getParameter("activeDescribe");
		logger.info("获取到的 activeName 为:" + activeName);
		logger.info("获取到的 startTime 为:" + startTime);
		logger.info("获取到的 endTime 为:" + endTime);
		logger.info("获取到的 activeType 为:" + activeType);
		logger.info("获取到的 activeNeedIntegral 为:" + activeNeedIntegral);
		logger.info("获取到的图片 activeImg 为:" + activeImg);
		logger.info("获取到的图片 activeDescribe 为:" + activeDescribe);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String imgName = df.format(new Date()) + ".jpg";
		publicBenefit.setActiveName(activeName);
		publicBenefit.setStartTime(startTime);
		publicBenefit.setEndTime(endTime);
		publicBenefit.setActiveType(activeType);
		publicBenefit.setActiveNeedIntegral(activeNeedIntegral);
		publicBenefit.setEx1(imgName);
		publicBenefit.setEx2(activeDescribe);
		int returnFlag = updateUserService.createActive(publicBenefit);
		generateImage(activeImg.split(",")[1], "/Users/liyunjian/Downloads/文件/开发工具/apache-tomcat-7.0.82-casServer/webapps/wtpwebapps/backstageAdmin/images/" + imgName);
		if (returnFlag == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 解码base64生成图片
	 * @param imgStr
	 * @param path
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path){
		if (imgStr == null) {
			return false;
		} else {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// 解密
				byte[] b = decoder.decodeBuffer(imgStr);
				// 处理数据
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						b[i] += 256;
					}
				}
				OutputStream out = new FileOutputStream(path);
				out.write(b);
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	
	/**
	 * 物资捐献审核
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("adminIndexDonation")
	public String indexDonation(Model model,HttpServletRequest request){
		int page;
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} else {
			page = 1;
		}
		PageHelper.startPage(page,3);
		List<Donation> donationList = updateUserService.selectAllDonation();
		PageInfo<Donation> p=new PageInfo<Donation>(donationList);
		model.addAttribute("donationList", donationList);
		model.addAttribute("pagehelper",p);
		return "indexDonation";
	}
	
	/**
	 * 审核通过
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("passApply")
	public String passApply(HttpServletRequest request,Model model){
		String donationIdStr = request.getParameter("donationId");
		int donationId = Integer.parseInt(donationIdStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		int returnFlag = updateUserService.passApply(donationId,df.format(new Date()));
		if (returnFlag == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 审核不通过
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("refuseApply")
	public String refuseApply(HttpServletRequest request,Model model){
		String donationIdStr = request.getParameter("donationId");
		int donationId = Integer.parseInt(donationIdStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		int returnFlag = updateUserService.refuseApply(donationId,df.format(new Date()));
		if (returnFlag == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 打开用户行为分析页面
	 * @return
	 */
	@RequestMapping("adminIndexChart")
	public String showChart(){
		return "indexChart";
	}
	
	/**
	 * 更改用户账号状态
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("countChart")
	public List<Map> countChart(HttpServletRequest request){
		List<Map> list=updateUserService.countChart();
		return list;
	}
	
}
