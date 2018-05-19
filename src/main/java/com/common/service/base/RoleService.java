/*
 * @(#)RoleService.java 2018年5月19日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.base;

import java.util.List;
import java.util.Set;

import com.common.command.RoleCommand;
import com.common.pojo.base.Role;
import com.common.pojo.base.User;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午2:30:51
 * @version V1.0.0 description：
 * 
 */
public interface RoleService {

	Role getRole(Long roleId);

	int createRole(RoleCommand roleCommand);

	int deleteRole(Long roleId);

	int updateRole(Role role, RoleCommand roleCommand);

	int initAdminRole(User user, Set<String> permissions);

	List<Role> getAllRole();

}
