package com.test.service;

import java.util.List;

import com.test.entity.PublicBenefit;
import com.test.entity.UserIP;
import com.test.entity.Users;

public interface PublicBenefitService {
	
	public List<PublicBenefit> selectAllActive();
	
	public PublicBenefit selectById(int activeId);
	
	public Users selectUserInfo(String userName);
	
	public int updateHaving(int id,int integral);
	
	public int updateSessionID(String userName,String sessionId);
	
	public List<UserIP> selectSessionID();
	
	public int selectActiveStatue(int activeId);
}
