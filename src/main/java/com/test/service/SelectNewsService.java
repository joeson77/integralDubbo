package com.test.service;
/**
 *查询新闻接口
 * @author Xiao
 * */
import java.util.List;

import com.test.entity.News;
public interface SelectNewsService {
	public List<News> selectNews();

}
