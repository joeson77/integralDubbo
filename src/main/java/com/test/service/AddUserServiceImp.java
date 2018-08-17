package com.test.service;

import com.test.entity.Users;
import com.test.until.QuartzJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.dao.AddUserMapper;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:02:53
 *
 * 功能描述： 用户注册接口实现类
 * 
 * 版本： V1.0
 */
public class AddUserServiceImp implements RegisterService {
	@Autowired
	AddUserMapper regesiterUser;
	private Logger loggerInfo = Logger.getLogger(QuartzJob.class);

	public int registerUser(Users user) {
		// TODO Auto-generated method stub
		int temp = regesiterUser.registerUser(user);
		if (user == null) {
			loggerInfo.info("user是空" + "无法注册");
		} else {
			loggerInfo.info(user.getUserName() + "注册成功:" + "" + "受影响的行数有:" + temp + "条!");
		}
		return temp;

	}

}
