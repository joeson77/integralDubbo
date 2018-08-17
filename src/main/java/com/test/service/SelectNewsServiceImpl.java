package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.dao.NewsMapper;

import com.test.entity.News;

/**
 * 
 * 
 * ���ߣ�ChenJianrong
 *
 * ����ʱ�䣺2018��8��9�� ����7:07:45
 *
 * ���������� ��ѯ���ŷ���ʵ����
 * 
 * �汾�� V1.0
 */
public class SelectNewsServiceImpl implements SelectNewsService {
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(SelectNewsServiceImpl.class);
	@Autowired
	NewsMapper userMapper;

	public List selectNews() {
		List<News> newsList = new ArrayList<News>();
		newsList = userMapper.selectNews();
		return newsList;
	}

}
