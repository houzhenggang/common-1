package com.common.dao;

import org.springframework.stereotype.Repository;

import com.common.pojo.UserAddress;

@Repository
public interface UserAddressDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}