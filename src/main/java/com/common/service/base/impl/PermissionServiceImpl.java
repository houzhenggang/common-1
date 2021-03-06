/*
 * @(#)PermissionServiceImpl.java 2018年5月19日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.base.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.PermissionCommand;
import com.common.dao.base.PermissionDAO;
import com.common.dao.base.RolePermissionDAO;
import com.common.dao.base.UserRoleDAO;
import com.common.pojo.base.Permission;
import com.common.pojo.base.RolePermission;
import com.common.pojo.base.User;
import com.common.service.base.PermissionService;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午8:40:36
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Long ADMIN_ID = 1L;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Override
	public Permission getPermission(int permissionId) {
		return permissionDAO.selectByPrimaryKey(permissionId);
	}

	@Override
	public int createPermission(PermissionCommand command) {
		Permission permission = new Permission();
		permission.setParentid(command.getParentid());
		permission.setName(command.getName());
		permission.setElement(command.getElement());
		permission.setDescription(command.getDescription());
		int result = permissionDAO.insertSelective(permission);
		if (result == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deletePermission(int permissionId) {
		int result = permissionDAO.deleteByPrimaryKey(permissionId);
		if (result == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updatePermission(Permission permission, PermissionCommand command) {
		permission.setParentid(command.getParentid());
		permission.setName(command.getName());
		permission.setElement(command.getElement());
		permission.setDescription(command.getDescription());
		int result = permissionDAO.updateByPrimaryKeySelective(permission);
		if (result == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Permission> getAllPermission() {
		return permissionDAO.getAllPermission();
	}

	@Override
	public List<Permission> getParentPermission() {
		return permissionDAO.getParentPermission();
	}

	@Override
	public List<String> selectPermissionsByRoleId(Long roleId) {
		return rolePermissionDAO.selectPermissionsByRoleId(roleId);
	}

	@Override
	public Long initAdminRole(User user, Set<String> permissions) {
		List<Long> list = userRoleDAO.selectRoleIdsByUserId(user.getId());
		if (list != null && !list.isEmpty()) {
			// 拥有超级管理员1L的角色才可以进行初始化
			if (list.contains(ADMIN_ID)) {
				//首先清理权限
				rolePermissionDAO.deletePermissionsByRoleId(ADMIN_ID);
				//重新赋予权限
				RolePermission rolePermission = null;
				for (String element : permissions) {
					rolePermission = new RolePermission();
					rolePermission.setRoleId(ADMIN_ID);
					rolePermission.setElement(element);
					rolePermissionDAO.insertSelective(rolePermission);
				}
				return 1L;
			}
		}
		return 0L;
	}
	
}
