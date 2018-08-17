package com.test.service;

/**
 * 用户查询明细接口
 * */
import java.util.HashMap;

import com.test.entity.Integral;
import com.test.entity.Users;

public interface SelectRecordService {

	public HashMap<String,Object> selectRecord(int pre, int total,Users users);
	
	public int selectRecordByFlag(Integral integral);
	
}