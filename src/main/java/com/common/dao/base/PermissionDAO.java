/*
 * @(#) PermissionMapper.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.base.Permission;

@Repository
public interface PermissionDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> getAllPermission();
    
    List<Permission> getParentPermission();
}