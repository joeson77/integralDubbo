package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.NewsMapper;
import com.test.entity.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsMapper newsDao;
	
	public List<News> selectAllNews() {
		return newsDao.selectAllNews();
	}

}
