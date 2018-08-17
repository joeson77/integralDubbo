package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.entity.Donation;
import com.demo.entity.Integral;
import com.demo.entity.PublicBenefit;
import com.demo.entity.Users;

public interface UpdateUserMapper {
	
	List<Users> selectAllUsers();
	
	int updateUserState(Users users);
	
	Users selectUserInfoById(int id);
	
	int updateUserInfo(Users users);
	
	int deleteUserById(int id);
	
	List<Integral> selectAllRecord(Integral integral);
	
	List<PublicBenefit> selectAllActive();
	
	int shutDownActive(int activeId);
	
	int startActive(int activeId);
	
	int createActive(PublicBenefit publicBenefit);
	
	List<Donation> selectAllDonation();
	
	int passApply(int id,String date);
	
	int refuseApply(int id,String date);
	
	List<Map> countChart();
}
