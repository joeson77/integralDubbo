package com.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.test.entity.News;
import com.test.service.SelectNewsService;
import com.test.util.RedisUtils2;

import redis.clients.jedis.Jedis;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月26日 下午2:11:11
 *
 * 功能描述： TODO
 * 
 * 版本： V1.0
 */
@Controller
@RequestMapping("/news/*")
public class NewsController {
	@Autowired
	SelectNewsService selectNewsService;

	protected static Logger log = LoggerFactory.getLogger(NewsController.class);

	Jedis jedis = RedisUtils2.getJedis();

	// 查询新闻入口
	@ResponseBody
	@RequestMapping(value = "/index.do", method = RequestMethod.POST)
	public Map<String, Object> selectNews(ModelAndView view, HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		List<News> result = null;
		String newsListRedis = jedis.get("newsList");
		result = JSON.parseArray(newsListRedis, News.class);
		// 若redis中没有，则从数据库中获取并放入到Redis数据库中
		if (result == null || result.size() == 0) {
			result = selectNewsService.selectNews();
			String json = JSON.toJSONString(result);
			log.info("从数据库中获取集合存放到Redis中");
			jedis.set("newsList", json);
			jedis.expire("newsList", 300);
		} else {
			log.info("redis中键newsList的剩余生存时间：" + jedis.ttl("newsList"));
			log.info("从Redis中获取数据");
		}
		log.info("调用查询新闻开始，查询到的记录数为：" + result.size());
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (result.size() > 0) {
			jsonMap.put("flag", "true");
			jsonMap.put("newsList", result);
			return jsonMap;
		} else {
			jsonMap.put("falg", "false");
			return jsonMap;
		}
	}

}
