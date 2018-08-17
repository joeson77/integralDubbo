package com.test.provider;

import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:02:00
 *
 * 功能描述： 开启服务提供者入口
 * 
 * 版本： V1.0
 */
public class Provider {
	static {
		ClassLoader classLoader = Provider.class.getClassLoader();
		URL resource = classLoader.getResource("log4j.properties");
		PropertyConfigurator.configure(resource);
	}

	public static void main(String args[]) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/*.xml");
		context.start();
		System.in.read();
	}
}
