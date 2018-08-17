package com.test.dao;

import java.util.List;

import com.test.entity.News;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:06:38
 *
 * 功能描述： 查询新闻mapper接口
 * 
 * 版本： V1.0
 */
public interface NewsMapper {

	public List<News> selectNews();

}
