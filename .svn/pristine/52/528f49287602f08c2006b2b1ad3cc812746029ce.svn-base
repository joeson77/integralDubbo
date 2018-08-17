package com.cam.query;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.cam.util.DateUtil;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月29日 下午2:55:09
 *
 * 功能描述： Donate查询实体类
 * 
 * 版本： V1.0
 */
public class DonateQueryObject extends QueryObject {
	private String title;
	private String username;
	private String type;
	private Integer status;
	private Date beginDate;
	private Date endDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		if (endDate != null) {
			return DateUtil.endOfDay(endDate);
		}
		return null;
	}

	public String getBeginDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (beginDate != null) {
			return sdf.format(beginDate);
		}
		return null;
	}

	@Override
	public String toString() {
		return "DonateQueryObject [title=" + title + ", username=" + username + ", type=" + type + ", status=" + status
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}

}
