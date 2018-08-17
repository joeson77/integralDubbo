package com.test.util;

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

import com.test.entity.News;
import com.test.service.NewsService;

/**
 * Quartz定时器定时触发增加对用新闻的点击量
 * @author 厉昀键
 * Created on 2018年7月17日
 * 类说明
 *
 */
public class QuartzJob {
	
	@Autowired
	NewsService newsService;
	
	private Logger logger = Logger.getLogger(QuartzJob.class);
	
	private VelocityEngine velocityEngine = new VelocityEngine();
	
	private int count = 1;
	
	Map<String, String> model;
	
	public void work(){
		
		Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);
	    
//	    File file = new File("/Users/liyunjian/Downloads/文件/开发工具/apache-tomcat-7.0.79/webapps/dubbo-customer01/html");
//	    File[] fileList = file.listFiles();
//	    for (File file2 : fileList) {
//			file2.delete();
//		}
		
		List<News> newsList = newsService.selectAllNews();
		for (int i = 0; i < newsList.size(); i++) {
			model = new HashMap<String, String>();
			model.put("key1", newsList.get(i).getNewsTitle());
			model.put("title", newsList.get(i).getNewsTitle());
			logger.info("newsUpdateTime = " + newsList.get(i).getNewsUpdateTime().substring(0, newsList.get(i).getNewsUpdateTime().length()-2));
			model.put("time", newsList.get(i).getNewsUpdateTime().substring(0, newsList.get(i).getNewsUpdateTime().length()-2));
			model.put("content", newsList.get(i).getNewsContext());
			String result = null;
			try {
				result = VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, "vm/baseInfoMiddle.vm", "UTF-8", model);
			} catch (VelocityException e) {
				e.printStackTrace();
			}
			
			try {
				String toPath = "/Users/liyunjian/Downloads/news/news" + newsList.get(i).getNewsName() + ".html";
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
		count = 1;
	}
	
}
