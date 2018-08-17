package com.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UpdateUserMapper;
import com.demo.entity.Donation;
import com.demo.entity.Integral;
import com.demo.entity.PublicBenefit;
import com.demo.entity.Users;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
	
	@Autowired
	UpdateUserMapper updateUserDao;
	
	public List<Users> selectAllUsers() {
		return updateUserDao.selectAllUsers();
	}

	public int updateUserState(Users users) {
		return updateUserDao.updateUserState(users);
	}

	public Users selectUserInfoById(int id) {
		return updateUserDao.selectUserInfoById(id);
	}

	public int updateUserInfo(Users users) {
		return updateUserDao.updateUserInfo(users);
	}

	public int deleteUserById(int id) {
		return updateUserDao.deleteUserById(id);
	}

	public List<Integral> selectAllRecord(Integral integral) {
		return updateUserDao.selectAllRecord(integral);
	}

	public List<PublicBenefit> selectAllActive() {
		return updateUserDao.selectAllActive();
	}

	public int shutDownActive(int activeId) {
		return updateUserDao.shutDownActive(activeId);
	}

	public int startActive(int activeId) {
		return updateUserDao.startActive(activeId);
	}

	public int createActive(PublicBenefit publicBenefit) {
		return updateUserDao.createActive(publicBenefit);
	}

	public List<Donation> selectAllDonation() {
		return updateUserDao.selectAllDonation();
	}

	public int passApply(int id,String date) {
		return updateUserDao.passApply(id,date);
	}

	public int refuseApply(int id,String date) {
		return updateUserDao.refuseApply(id,date);
	}

	public List<Map> countChart() {
		return updateUserDao.countChart();
	}

}
