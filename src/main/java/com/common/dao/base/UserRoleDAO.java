/*
 * @(#) UserRoleMapper.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.base.Role;
import com.common.pojo.base.UserRole;

@Repository
public interface UserRoleDAO {
    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    int insertSelective(UserRole record);
    
    List<Role> selectRolesByUserId(Long userId);
}