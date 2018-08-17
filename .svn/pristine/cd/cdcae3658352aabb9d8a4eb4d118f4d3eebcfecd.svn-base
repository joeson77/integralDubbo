package com.test.provider;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	// start provider  
    static{
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
