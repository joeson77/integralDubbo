package com.test.dao;

import java.util.List;

import com.test.entity.PublicBenefit;
import com.test.entity.UserIP;
import com.test.entity.Users;

public interface PublicBenefitMapper {
    int deleteByPrimaryKey(Integer activeId);

    int insert(PublicBenefit record);

    int insertSelective(PublicBenefit record);

    PublicBenefit selectByPrimaryKey(Integer activeId);

    int updateByPrimaryKeySelective(PublicBenefit record);

    int updateByPrimaryKey(PublicBenefit record);
    
    List<PublicBenefit> selectAllActive();
    
    Users selectUserInfo(String userName);
    
    int updateHaving(int id,int integral);
    
    int updateSessionID(String userName,String sessionId);
    
    List<UserIP> selectSessionID();
    
    int selectActiveStatue(int activeID);
}