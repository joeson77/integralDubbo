package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.PublicBenefitMapper;
import com.test.entity.PublicBenefit;
import com.test.entity.UserIP;
import com.test.entity.Users;

@Service
public class PublicBenefitServiceImpl implements PublicBenefitService {
	
	@Autowired
	PublicBenefitMapper publicBenefitDao;
	
	public List<PublicBenefit> selectAllActive() {
		return publicBenefitDao.selectAllActive();
	}

	public PublicBenefit selectById(int activeId) {
		return publicBenefitDao.selectByPrimaryKey(activeId);
	}

	public Users selectUserInfo(String userName) {
		return publicBenefitDao.selectUserInfo(userName);
	}

	public int updateHaving(int id, int integral) {
		return publicBenefitDao.updateHaving(id, integral);
	}

	public int updateSessionID(String userName, String sessionId) {
		return publicBenefitDao.updateSessionID(userName, sessionId);
	}

	public List<UserIP> selectSessionID() {
		return publicBenefitDao.selectSessionID();
	}

	public int selectActiveStatue(int activeId) {
		return publicBenefitDao.selectActiveStatue(activeId);
	}
	
}
