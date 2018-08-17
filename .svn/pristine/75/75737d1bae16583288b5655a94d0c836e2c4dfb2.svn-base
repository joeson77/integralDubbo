package com.cam.dao;

import com.cam.pojo.Users;
import java.util.List;
/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:47:49
 *
 * 功能描述： 用户mapper接口
 * 
 * 版本： V1.0
 */
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    Users selectByPrimaryKey(Integer id);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

	Users selectUserByUsername(String username);
}