package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.NewsMapper;
import com.demo.entity.News;
import com.demo.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsMapper newsDao;

	public List<News> selectAllNewsByType() {
		return newsDao.selectAllNewsByType();
	}

	public News selectNewsByPrimary(String id) {
		return newsDao.selectNewsByPrimary(id);
	}

	public void updateNews(String id, String title, String context, String type, String clickrate) {
		newsDao.updateNews(id, title, context, type, clickrate);
	}

	public void deleteNewsByPrimaryId(String id) {
		newsDao.deleteNewsByPrimaryId(id);
	}
}
