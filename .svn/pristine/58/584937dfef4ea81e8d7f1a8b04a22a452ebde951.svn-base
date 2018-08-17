package com.test.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.entity.UserIP;
import com.test.entity.Users;
import com.test.service.PublicBenefitService;

import redis.clients.jedis.Jedis;

/**
 * Quartz定时器定时触发增加对用新闻的点击量
 * @author 厉昀键
 * Created on 2018年8月1日
 * 类说明
 *
 */
public class QuartzJob {
	
	@Autowired
	PublicBenefitService publicBenefitService;
	
	Jedis jedis = RedisUtils2.getJedis();
	
	private Logger logger = Logger.getLogger(QuartzJob.class);
	
	public void work(){
		logger.info("销毁session调度器运行中....");
		List<UserIP> userIPs = publicBenefitService.selectSessionID();
		for (UserIP userIP : userIPs) {
			MySessionContext myc= MySessionContext.getInstance();
			HttpSession sess = myc.getSession(userIP.getServerOne());
			if (sess != null) {
				logger.info("session不为空");
				sess.invalidate();
				logger.info("删除 userName 为:" + userIP.getUserName() + " 的session成功!");
			} else {
				logger.info("session为空");
			}
		}
	}
	
}
