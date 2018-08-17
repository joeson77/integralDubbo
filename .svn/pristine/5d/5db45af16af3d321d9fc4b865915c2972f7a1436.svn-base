package com.cam.pojo;

import java.io.Serializable;
import java.util.Date;

import com.cam.util.Constant;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:46:19
 *
 * 功能描述： 捐献实体类
 * 
 * 版本： V1.0
 */
public class Donation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String title;

	private String type;

	private Date applyTime;

	private Date creditTime;

	private Integer status;

	private String description;

	private String ex1;

	private String ex2;

	private String ex3;

	private String telPhone;

	private Integer number;

	private String address;

	private String imgUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getCreditTime() {
		return creditTime;
	}

	public void setCreditTime(Date creditTime) {
		this.creditTime = creditTime;
	}

	public String getDonateStatusDispaly() {
		switch (this.status) {
		case Constant.DONATE_STATE_UNCREDIT:
			return "待审核";
		case Constant.DONATE_STATE_CREDITED:
			return "获得积分 ："+getIntegral();
		case Constant.DONATE_STATE_REJECTED:
			return "审核拒绝";
		default:
			return "错误状态";
		}
	}

	private Integer getIntegral() {
		switch (this.type) {
		case Constant.DONATE_TYPE_CLOTHES:
			return Constant.DONATE_TYPE_CLOTHES_SCORE*getNumber();
		case Constant.DONATE_TYPE_BOOK:
			return Constant.DONATE_TYPE_BOOK_SCORE*getNumber();
		case Constant.DONATE_TYPE_OTHER:
			return Constant.DONATE_TYPE_OTHER_SCORE*getNumber();
		default:
			return -1;
		}
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getEx1() {
		return ex1;
	}

	public void setEx1(String ex1) {
		this.ex1 = ex1 == null ? null : ex1.trim();
	}

	public String getEx2() {
		return ex2;
	}

	public void setEx2(String ex2) {
		this.ex2 = ex2 == null ? null : ex2.trim();
	}

	public String getEx3() {
		return ex3;
	}

	public void setEx3(String ex3) {
		this.ex3 = ex3 == null ? null : ex3.trim();
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone == null ? null : telPhone.trim();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", username=" + username + ", title=" + title + ", type=" + type + ", applyTime="
				+ applyTime + ", creditTime=" + creditTime + ", status=" + status + ", description=" + description
				+ ", ex1=" + ex1 + ", ex2=" + ex2 + ", ex3=" + ex3 + ", telPhone=" + telPhone + ", number=" + number
				+ ", address=" + address + ", imgUrl=" + imgUrl + "]";
	}
	
	
}