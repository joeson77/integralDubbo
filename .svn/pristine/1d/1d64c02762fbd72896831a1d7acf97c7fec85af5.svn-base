package com.cam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.cam.dao.DonationMapper;
import com.cam.pojo.Donation;
import com.cam.query.DonateQueryObject;
import com.cam.query.PageResult;
import com.cam.service.DonateService;


/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月28日 上午11:23:32
 *
 * 功能描述： 捐献服务实现类
 * 
 * 版本： V1.0 
 */
@Service("donateService")
public class DonateServiceImpl implements DonateService{
	@Autowired
	DonationMapper donationMapper;
	
	protected static Logger log=LoggerFactory.getLogger(DonateServiceImpl.class);
	
	@Override
	public int insert(Donation donate) {
		log.info("插入对象的属性值为："+donate.toString());
		return this.donationMapper.insert(donate);
	}
	
	@Override
	public PageResult query(DonateQueryObject qo) {
		int count=this.donationMapper.queryForCount(qo);
		log.info("查询获取用户捐款项目数为："+count);
		if(count>0){
			List<Donation> list=this.donationMapper.query(qo);
			return new PageResult(count, qo.getCurrentPage(),qo.getPageSize(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public List<Donation> selectAll() {
		return this.donationMapper.selectAll();
	}

	@Override
	public List<Donation> indexInit() {
		return this.donationMapper.indexInit();
	}
	
	@Override
	public List<Donation> selectDonateByType(String type) {
		log.info("传递过来的type参数值是："+type);
		return this.donationMapper.selectByType(type);
	}

	
	
}
