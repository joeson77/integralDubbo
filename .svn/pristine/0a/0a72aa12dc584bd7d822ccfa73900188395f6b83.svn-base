package com.cam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cam.pojo.Donation;
import com.cam.query.DonateQueryObject;
import com.cam.query.PageResult;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月28日 上午11:23:01
 *
 * 功能描述： 捐赠服务接口
 * 
 * 版本： V1.0
 */
public interface DonateService {

	public int insert(Donation donate);

	public PageResult query(DonateQueryObject qo);

	public List<Donation> selectAll();

	public List<Donation> indexInit();

	public List<Donation> selectDonateByType(String type);
}
