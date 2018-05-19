package com.common.command;

import com.common.core.model.BaseObject;

public class RoleCommand extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 角色ID
	private Long roleId;

	// 角色名称
	private String name;

	// 角色描述
	private String description;

	// 权限描述
	private String permissions;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

}
