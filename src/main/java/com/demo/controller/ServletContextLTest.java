package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextLTest implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("我是销毁方法。。。");    
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sct=sce.getServletContext(); 
		Map<String,String> depts=new HashMap<String,String>();
		depts.put("hello","我是初始化方法");
		System.out.println("======listener test is beginning=========");
	}

}
