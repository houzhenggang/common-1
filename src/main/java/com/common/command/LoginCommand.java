/*
 * @(#)LoginCommand.java 2018年5月19日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.command;

import com.common.core.model.BaseObject;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午10:23:42
 * @version V1.0.0 description：
 * 
 */
public class LoginCommand extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户名称
	private String username;

	//用户密码
	private String password;

	//验证码
	private String captcha;

	//是否记住
	private boolean rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
