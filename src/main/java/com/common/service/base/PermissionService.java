/*
 * @(#)PermissionService.java 2018年5月19日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.base;

import java.util.List;
import java.util.Set;

import com.common.command.PermissionCommand;
import com.common.pojo.base.Permission;
import com.common.pojo.base.User;


/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午8:37:53
 * @version V1.0.0
 * description：
 * 
 */
public interface PermissionService {

	Permission getPermission(int permissionId);
	
    int createPermission(PermissionCommand command);

    int deletePermission(int permissionId);

    int updatePermission(Permission permission, PermissionCommand command);
    
    List<Permission> getAllPermission();
    
    List<Permission> getParentPermission();
    
    List<String> selectPermissionsByRoleId(Long roleId);
    
    Long initAdminRole(User user, Set<String> permissions);
	
}
