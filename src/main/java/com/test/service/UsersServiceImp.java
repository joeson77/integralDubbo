package com.test.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.dao.UsersMapper;
import com.test.entity.Integral;
import com.test.entity.UserIP;
import com.test.entity.Users;
import com.test.service.LoginService;

/**
 * @author Xiao
 * time:2017-7-17
 * 鐢ㄦ埛鐧婚檰鎺ュ彛瀹炵幇绫�
 * 
 * */


	@Service
public class UsersServiceImp implements LoginService {
	//自动注入
	@Autowired
	UsersMapper UserDao;
	private Logger logger = Logger.getLogger(UsersServiceImp.class);
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	
	
	
	public Users selectUser(Users users) {
		//查找用户
		Users temp=UserDao.selectUser(users);
		//输出用户登陆日志
		if(temp!=null) {
			logger.info("查询到用户："+temp.getUserName()+",时间："+sim.format(new Date()));
			
		}else {
			logger.info("用户不存在");
		}
		return temp;
		
	}

	//记录、查询用户登陆的userName,IP
	public int insertIPForUser(String userName, String IP, String date) {
		// TODO Auto-generated method stub
		if(userName==null && IP==null) {
			logger.info("记录用户登陆信息，参数是null,返回-1");
			return -1;
		}else {
		return UserDao.insertIPForUser(userName, IP, date);
		}
	}
	
	//记录登陆信息
	public UserIP userIP(String userName, String IP) {
		// TODO Auto-generated method stub
		if(userName==null && IP==null) {
			logger.info("记录用户登陆IP参数是空，返回null");
			return null;
		}else {
			return UserDao.userIP(userName, IP);
	
			
		}
		
	}

	//查找积分
	public Users selectUserIntegral(Users users) {
		// TODO Auto-generated method stub
		if(users!=null) {
			return UserDao.selectUserIntegral(users);
		}else {
			logger.info("查找积分参数是空，返回null");
			return null;
		}
		
	}
	
	//修改用户状态
	public int updateUserOnlineStatue(UserIP userIP) {
		// TODO Auto-generated method stub
		if(userIP!=null) {
			return UserDao.updateUserOnlineStatue(userIP);
		}else {
			logger.info("更改用户登陆状态，参数是空，-1");
			return -1;
		}
	}
	
	//查找注册信息
	public int selectSignInInfo(Integral integral) {
		if(integral!=null) {
			return UserDao.selectSignInInfo(integral);
		}else {
			logger.info("查找用户注册信息，参数是空，-1");
			return -1;
		}
		
		
	}

	public int insertUserIP(UserIP userIP) {
		// TODO Auto-generated method stub
		return -1;
	}

}
