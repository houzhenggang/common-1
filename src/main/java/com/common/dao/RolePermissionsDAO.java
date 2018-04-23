/*
 * @(#) RolePermissionMapper.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao;

import org.springframework.stereotype.Repository;

import com.common.pojo.RolePermissions;

@Repository
public interface RolePermissionsDAO {
    int insert(RolePermissions record);

    int insertSelective(RolePermissions record);
}