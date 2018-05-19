/*
 * @(#) RoleMapper.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.base.Role;

@Repository
public interface RoleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> getAllRole();
}