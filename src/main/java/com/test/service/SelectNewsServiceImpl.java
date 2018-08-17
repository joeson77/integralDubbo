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
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:07:45
 *
 * 功能描述： 查询新闻服务实现类
 * 
 * 版本： V1.0
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
