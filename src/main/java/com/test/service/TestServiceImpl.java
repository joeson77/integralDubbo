package com.test.service;

import org.springframework.stereotype.Service;

import com.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	public String test() {
        System.out.println("test success");
        return "updateInter";  
    }

}
