package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UpdateIntegralMapper;
import com.test.entity.Integral;
import com.test.entity.Users;

@Service
public class UpdateIntegralServiceImpl implements UpdateIntegralService {

	@Autowired
	UpdateIntegralMapper updateIntegralDao;
	
	public int updateIntegral(Users users) {
		return updateIntegralDao.updateUserIntegral(users);
	}

}