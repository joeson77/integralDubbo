package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.AddRecordMapper;
import com.test.entity.Integral;

@Service
public class AddRecordServiceImpl implements AddRecordService {
	
	@Autowired
	AddRecordMapper addRecordDao;
	
	public int addRecord(Integral integral) {
		return addRecordDao.addRecord(integral);
	}

}
