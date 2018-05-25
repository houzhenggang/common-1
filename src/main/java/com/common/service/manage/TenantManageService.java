/*
 * @(#)TenantManageService.java 2018年5月25日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.TenantCommand;
import com.common.pojo.Tenant;

/**
 *
 * @author Administrator
 * @date 2018年5月25日 上午11:13:08
 * @version V1.0.0
 * description：
 * 
 */
public interface TenantManageService {

	Tenant getTenant(Long id);

    void createTenant(TenantCommand tenantCommand, String creator, String imgPath);

    void deleteTenant(Long id);

    void updateTenant(Tenant tenant);
    
    void updateTenant(Tenant tenant, TenantCommand tenantCommand);
    
    List<Tenant> getAllTenant();
	
}
