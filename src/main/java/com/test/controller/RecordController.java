package com.test.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.github.pagehelper.PageInfo;
import com.test.entity.Integral;
import com.test.entity.Users;
import com.test.service.SelectRecordService;
import com.test.util.JavaWebToken;
import com.test.util.RedisUtils2;

import redis.clients.jedis.Jedis;


/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月23日 下午4:57:01
 *
 * 功能描述： 积分变更明细控制器
 * 
 * 版本： V1.0
 */
@Controller
@RequestMapping("/record/*")
public class RecordController {
	@Autowired
	SelectRecordService selectRecordService;
	
	protected static Logger log = LoggerFactory.getLogger(RecordController.class);
	
	Jedis jedis2 = RedisUtils2.getJedis();
	
	//测试查询积分变更明细
	@ResponseBody
	@RequestMapping(value="/select.do",method = RequestMethod.POST)
    public Map < String , Object > selectIntegralChangedRecord(ModelAndView view,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String username = request.getParameter("userName");
		//获取前台传过来的开始日期，转换为Date
		String beginTime=request.getParameter("beginTime");
		//获取前台传过来的截至日期，转换为Date
		String endTime=request.getParameter("endTime");
		//获取flag字段
		String flag=request.getParameter("flag");
		log.info("收取的flag字段为："+flag);
		int page;//当前页数
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} else {
			page = 1;
		}
		//判断beginTime是否为空
		Users user=new Users();
		user.setUserName(username);
		if(StringUtils.hasLength(beginTime)){
			//拼接beginTime
//			String beginTimeStr = beginTime.concat(" 00:00:00");
			log.info("接收到的查询开始时间为："+beginTime);
			user.setEx1(beginTime);
		}
		if(StringUtils.hasLength(endTime)){
			//拼接endTime
			String endTimeStr = endTime.concat(" 23:59:59");
			log.info("接收到的查询截至时间为："+endTimeStr);
			user.setEx2(endTimeStr);
		}
		if(StringUtils.hasLength(flag)){
			user.setEx3(flag);
		}
		List<Integral> result = null;
		PageInfo<Integral> pageInfo = null;
		String tokenAgain = "";
		try {
			HashMap<String,Object> map = selectRecordService.selectRecord(page,3,user);
			result = (List<Integral>) map.get("list");
			pageInfo = (PageInfo<Integral>) map.get("pageInfo");
			log.info(pageInfo.toString());
			log.info("调用查询积分变更明细接口开始，查询到的记录数为："+result.size() );
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("userName", username);
			m.put("date", df.format(new Date()));
			//用户名载体生成token
			tokenAgain = JavaWebToken.createJavaWebToken(m);
			//token存到redis
			jedis2.set(username, tokenAgain);
			log.info("查询用户积分变更明细新获得token字符串为:" + tokenAgain);
		} catch (RuntimeException e) {
			Map<String, Object> m = new HashMap<String, Object>();
			Map < String , Object > jsonMap = new HashMap< String , Object>();
			m.put("userName", username);
			m.put("date", df.format(new Date()));
			//用户名载体生成token
			tokenAgain = JavaWebToken.createJavaWebToken(m);
			//token存到redis
			jedis2.set(username, tokenAgain);
			log.info("查询用户积分变更明细新获得token字符串为:" + tokenAgain);
			log.error("调用查询积分变更明细接口异常！"+e.getMessage());
			jsonMap.put("errorMsg", "查询失败");
			jsonMap.put("errorCode", "1111");
			jsonMap.put("token", tokenAgain);
			return jsonMap;
		}
		Map < String , Object > jsonMap = new HashMap< String , Object>();
		if(result.size()>0){
			jsonMap.put("errorMsg", "查询成功");
			jsonMap.put("errorCode", "0000");
			jsonMap.put("pageInfo", pageInfo);
			jsonMap.put("recordList", result);
			jsonMap.put("token", tokenAgain);
			return jsonMap;
		}else{
			jsonMap.put("recordList", null);
			jsonMap.put("token", tokenAgain);
			jsonMap.put("errorMsg", "没有兑换记录");
			jsonMap.put("errorCode", "0000");
			return jsonMap;
		}
	}
   }	

	

