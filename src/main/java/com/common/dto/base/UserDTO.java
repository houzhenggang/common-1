/*
 * @(#)UserDTO.java 2018年5月20日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.dto.base;

import java.util.List;

import com.common.core.model.BaseObject;
import com.common.pojo.base.Role;

/**
 *
 * @author Administrator
 * @date 2018年5月20日 上午11:20:27
 * @version V1.0.0 description：
 * 
 */
public class UserDTO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//据数ID
    private Long id;

    //用户名称
    private String username;

    //用户邮箱
    private String email;

    //用户密码
    private String password;
    
    //用户关联角色
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
