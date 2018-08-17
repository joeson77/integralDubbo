package com.cam.dao;

import com.cam.pojo.Donation;
import com.cam.query.DonateQueryObject;

import java.util.List;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:47:43
 *
 * 功能描述： 捐赠mapper接口
 * 
 * 版本： V1.0
 */
public interface DonationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Donation record);

    Donation selectByPrimaryKey(Integer id);

    List<Donation> selectAll();

    int updateByPrimaryKey(Donation record);

	int queryForCount(DonateQueryObject qo);

	List<Donation> query(DonateQueryObject qo);

	List<Donation> indexInit();

	List<Donation> selectByType(String type);
}