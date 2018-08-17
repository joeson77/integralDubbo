package com.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.dao.SelectRecordMapper;
import com.test.entity.Integral;
import com.test.entity.Users;

@Service
public class SelectRecordServiceImpl implements SelectRecordService {
	
	@Autowired
	SelectRecordMapper selectRecordDao;

	public HashMap<String,Object> selectRecord(int pre, int total,Users users) {
		PageHelper.startPage(pre,total);
		List<Integral> list = selectRecordDao.selectRecord(users);
		PageInfo<Integral> pageInfo = new PageInfo<Integral>(list);
		System.out.println(pageInfo);
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("list", list);
		info.put("pageInfo", pageInfo);
		return info;
	}

	public int selectRecordByFlag(Integral integral) {
		return selectRecordDao.selectRecordByFlag(integral);
	}

}
