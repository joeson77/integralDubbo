package com.test.dao;

import java.util.List;

import com.test.entity.Integral;
import com.test.entity.Users;

public interface SelectRecordMapper {
	
	List<Integral> selectRecord(Users users);
	
	int selectRecordByFlag(Integral integral);
}
