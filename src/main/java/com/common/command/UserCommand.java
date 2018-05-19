package com.common.command;

import com.common.core.model.BaseObject;

public class UserCommand extends BaseObject{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户名称
    private String username;

    //用户邮箱
    private String email;

    //用户密码
    private String password;

    //关联角色IDs
	private String roleIds;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
