package com.demo.util;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.demo.entity.News;
import com.demo.service.NewsService;

/**
 * Quartz定时器定时触发生成静态页
 * @author 厉昀键
 * Created on 2018年1月10日
 * 类说明
 *
 */
public class QuartzJob {
	
	@Autowired
	NewsService newsService;
	
	private Logger logger = Logger.getLogger(QuartzJob.class);
	
	private VelocityEngine velocityEngine;
	private int count = 1;
	Map<String, String> model;
	
	public void work(){
		
		Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);
		
		List<News> newsList = newsService.selectAllNewsByType();
		for (int i = 0; i < newsList.size(); i++) {
			model = new HashMap<String, String>();
			model.put("key1", newsList.get(i).getTitle());
			model.put("title", newsList.get(i).getTitle());
			model.put("time", newsList.get(i).getUpdateTime());
			model.put("content", newsList.get(i).getContent());
			String result = null;
			try {
				result = VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, "baseInfoMiddle.vm", "UTF-8", model);
			} catch (VelocityException e) {
				e.printStackTrace();
			}
			
			try {
				String toPath = "/Users/liyunjian/Users/liyunjian/Downloads/eclipseProcedure/newsDisplay/src/main/webapp/html/news" + newsList.get(i).getNewsName() + ".html";
				PrintWriter writer = new PrintWriter(toPath,"utf-8");
				writer.println(result);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			logger.info("Quartz任务调度生成文件:" + count++);
		}
		
		logger.info("当前任务调度时间是:" + dateString);
	}
	
	
	
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}
