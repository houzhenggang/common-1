/*
 * @(#)TenantManageServiceImpl.java 2018年5月25日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.TenantCommand;
import com.common.constant.StatusCode;
import com.common.dao.TenantDAO;
import com.common.pojo.Tenant;
import com.common.service.manage.TenantManageService;
import com.common.util.NewDate;

/**
 *
 * @author Administrator
 * @date 2018年5月25日 下午2:06:33
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class TenantManageServiceImpl implements TenantManageService {

	@Autowired
	private TenantDAO tenantDAO;
	
	@Override
	public Tenant getTenant(Long id) {
		return tenantDAO.selectByPrimaryKey(id);
	}

	@Override
	public void createTenant(TenantCommand tenantCommand, String creator, String imgPath) {
		Tenant tenant = new Tenant();
		tenant.setName(tenantCommand.getName());
		tenant.setAge(tenantCommand.getAge());
		tenant.setPhoto(imgPath);
		tenant.setStatus(StatusCode.STATUS_ON.getCode());
		tenant.setCreator(creator);
		tenant.setCreateTime(NewDate.getDateTime());
		tenant.setUpdateTime(tenant.getCreateTime());
		tenantDAO.insertSelective(tenant);
	}

	@Override
	public void deleteTenant(Long id) {
		tenantDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateTenant(Tenant tenant) {
		tenantDAO.updateByPrimaryKeySelective(tenant);		
	}
	
	@Override
	public void updateTenant(Tenant tenant, TenantCommand tenantCommand) {
		tenant.setName(tenantCommand.getName());
		tenant.setAge(tenantCommand.getAge());	
		tenant.setStatus(StatusCode.STATUS_ON.getCode());
		tenant.setUpdateTime(NewDate.getDateTime());
		tenantDAO.updateByPrimaryKeySelective(tenant);
	}

	@Override
	public List<Tenant> getAllTenant() {
		return tenantDAO.getAllTenant();
	}

}
