package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UpdateUserInfoMapper;
import com.test.entity.Users;

@Service
public class UpdateUserInfoServiceImpl implements UpdateUserInfoService {
	
	@Autowired
	UpdateUserInfoMapper updateUserInfoDao;
	
	public int updateUserInfo(Users users) {
		return updateUserInfoDao.updateUserInfo(users);
	}

}
