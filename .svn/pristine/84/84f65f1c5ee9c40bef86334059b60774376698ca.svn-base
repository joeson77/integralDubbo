package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.entity.Donation;
import com.demo.entity.Integral;
import com.demo.entity.PublicBenefit;
import com.demo.entity.Users;

public interface UpdateUserService {
	
	public List<Users> selectAllUsers();

	public int updateUserState(Users users);
	
	public Users selectUserInfoById(int id);
	
	public int updateUserInfo(Users users);
	
	public int deleteUserById(int id);
	
	public List<Integral> selectAllRecord(Integral integral);
	
	public List<PublicBenefit> selectAllActive();
	
	public int shutDownActive(int activeId);
	
	public int startActive(int activeId);
	
	public int createActive(PublicBenefit publicBenefit);
	
	public List<Donation> selectAllDonation();
	
	public int passApply(int id,String date);
	
	public int refuseApply(int id,String date);
	
	public List<Map> countChart();
}
