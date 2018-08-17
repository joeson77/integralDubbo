package com.cam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cam.dao.UsersMapper;
import com.cam.pojo.Users;
import com.cam.service.UserService;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月6日 下午7:13:09 
 *
 * 功能描述： 用户服务实现类
 * 
 * 版本： V1.0 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public Users selectUserByUsername(String username) {
		return this.usersMapper.selectUserByUsername(username);
	}

}
