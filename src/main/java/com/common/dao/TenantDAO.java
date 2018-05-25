/*
 * @(#) TenantMapper.java 2018年05月25日
 *
 * Copyright (c), 2016 深圳市万睿智能科技有限公司（Shenzhen Wan Rui Intelligent Technology Co., Ltd.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.Tenant;

@Repository
public interface TenantDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
    
    List<Tenant> getAllTenant();
}