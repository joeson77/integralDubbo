package com.test.dao;
import com.test.entity.Integral;
import com.test.entity.UserIP;
/**
 * @author Xiao
 * time:2017-7-17
 *  利用mapper 代理的方式操作数据库
 * */
import com.test.entity.Users;
public interface UsersMapper {
	//查询用户
	public Users selectUser(Users users); 
	//记录用户登陆
	public int insertIPForUser(String userName, String userIP, String insertTime);
	public UserIP userIP(String userName,String IP);
	public Users selectUserIntegral(Users users);
	//更改用户状态
	public int updateUserOnlineStatue(UserIP userIP);
	//注册信息
	public int selectSignInInfo(Integral integral);
}
