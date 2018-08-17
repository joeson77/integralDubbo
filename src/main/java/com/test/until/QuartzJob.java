package com.test.until;

import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.entity.Users;
import com.test.service.RegisterService;
import redis.clients.jedis.Jedis;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:03:12
 *
 * 功能描述： 定时器
 * 
 * 版本： V1.0
 */
public class QuartzJob {

	@Autowired
	RegisterService registerService;

	Users users = new Users();

	Jedis jedis = RedisConnect.getJedis();

	private Logger loggerInfo = Logger.getLogger(QuartzJob.class);

	public void work() {
		// 注册用户的日志，影响的行
		/*
		 * Map<String, String> map= new HashMap<String, String>(); // 设置键为01
		 * String result = jedis.hmset("01", map);
		 * System.out.println("存储后方法的返回值：" + result); System.out.println("取出值："
		 * + jedis.hgetAll("01"));
		 */
		Set<byte[]> keySet = jedis.keys("*".getBytes());
		byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
		for (byte[] bs : keys) {
			String s = new String(bs);
			System.out.println("------" + s + "-----");
			int userId = Integer.parseInt(s);
			String fromRedis = jedis.get(s);
			String[] as = fromRedis.split(",");// 将从redis中获取的字符串拆分为用户名和密码
			String userName = as[0];// 用户名
			String userPwd = as[1];// 密码
			loggerInfo.info("用户ID为:" + userId + ",用户名:" + userName + ",注册");
			users.setId(userId);
			users.setUserName(userName);
			users.setUserPwd(userPwd);
			int returnInt = registerService.registerUser(users);
			loggerInfo.info("注册成功:" + "" + "受影响的行数有:" + returnInt + "条!");
			// 消除redis里面的key
			jedis.del(s);
			loggerInfo.info("已经删除redis里面key：" + s + "的数据");

		}

	}
}
