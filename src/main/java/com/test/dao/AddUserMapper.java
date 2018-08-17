package com.test.dao;

import com.test.entity.Users;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:00:54
 *
 * 功能描述： 添加用户mapper接口
 * 
 * 版本： V1.0
 */
public interface AddUserMapper {

	public int registerUser(Users user);
}