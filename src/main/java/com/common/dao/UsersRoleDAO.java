/*
 * @(#) UsersRoleMapper.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao;

import com.common.pojo.UsersRole;

public interface UsersRoleDAO {
    int deleteByPrimaryKey(UsersRole key);

    int insert(UsersRole record);

    int insertSelective(UsersRole record);
}