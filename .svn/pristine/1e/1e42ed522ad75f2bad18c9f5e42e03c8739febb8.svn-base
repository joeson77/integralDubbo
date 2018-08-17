package com.test.util;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.entity.Integral;
import com.test.entity.Users;
import com.test.service.AddRecordService;
import com.test.service.UpdateIntegralService;

import redis.clients.jedis.Jedis;

/**
 * Quartz定时器定时触发增加对用新闻的点击量
 * @author 厉昀键
 * Created on 2018年7月17日
 * 类说明
 *
 */
public class QuartzJob {
	
	@Autowired
	UpdateIntegralService updateIntegralService;
	@Autowired
	AddRecordService addRecordService;
	
	Users users = new Users();
	
	Integral integral = new Integral();

	Jedis jedis = RedisUtils.getJedis();
	
	private Logger logger = Logger.getLogger(QuartzJob.class);
	
	public void work(){
		Set<byte[]> keySet = jedis.keys("*".getBytes());
	    byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
	    for (byte[] bs : keys) {
	    		String s = new String(bs);
	    		System.out.println("------"+ s +"-----");
	    		if (s.substring(0,1).equals("A")) {
				//添加消费明细
	    			String fromRedis = jedis.get(s);
		    		String[] as = fromRedis.split(",");//将从redis中获取的字符串拆分
		    		String userName = as[0];//用户名
		   		String updateTime = as[1];//更新日期
		   		String userIntegralStr = as[2];
		   		int userIntegralInt = Integer.parseInt(userIntegralStr);//用户积分
		   		String userIntegralType = as[3];//积分使用详情
		   		logger.info("用户名为:" + userName + ",更新日期:" + updateTime + ",用户积分:" + userIntegralInt + ",积分使用情况:" + userIntegralType);
		   		integral.setUserName(userName);
		   		integral.setUpdateTime(updateTime);
		   		integral.setIntegral(userIntegralInt);
		   		integral.setEx1(userIntegralType);
		   		int returnFlag = addRecordService.addRecord(integral);
		   		logger.info("受影响的行数有:" + returnFlag + "条!");
		   		jedis.del(s);
		   		logger.info("已删除key为:" + s + "  的数据");
			} else if(s.substring(0,1).equals("U")){
				//用户积分修改积分
				String userName = s.substring(1, s.length());//截取用户name
	    			String fromRedis = jedis.get(s);
	    			String[] as = fromRedis.split(",");//将从redis中获取的字符串拆分
	    			String userIntegralStr = as[0];//用户积分string类型
	    			String updateIntegralStr = as[1];//用户修改积分string类型
	    			int userIntegral = Integer.parseInt(userIntegralStr);//用户原积分
	    			int updateIntegral = Integer.parseInt(updateIntegralStr);//修改的积分数
	    			int total = userIntegral + updateIntegral;
	    			logger.info("用户name为:" + userName + ",用户积分:" + total);
	    			users.setUserName(userName);
	    			users.setUserIntegral(total);
	    			int returnInt = updateIntegralService.updateIntegral(users);
	    			logger.info("受影响的行数有:" + returnInt + "条!");
	    			jedis.del(s);
	    			logger.info("已删除key为:" + s + "  的数据");
			}else {
				logger.info("redis中存在错误数据!");
			}
		}
	}
	
}
