package com.demo.entity;

import java.io.Serializable;

public class Integral implements Serializable {
	
	private Integer id;//主键id
	
	private String userName;//用户名
	
	private String updateTime;//积分更新时间
	
	private Integer integral;//用户积分
	
	private String ex1;//扩展字段1
	
	private String ex2;//扩展字段2
	
	private String ex3;//扩展字段3

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getEx1() {
		return ex1;
	}

	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}

	public String getEx2() {
		return ex2;
	}

	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}

	public String getEx3() {
		return ex3;
	}

	public void setEx3(String ex3) {
		this.ex3 = ex3;
	}

}
