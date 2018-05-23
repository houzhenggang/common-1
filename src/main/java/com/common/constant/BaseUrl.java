/*
 * @(#)AppUrl.java 2018年5月16日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.constant;

/**
 *
 * @author Administrator
 * @date 2018年5月16日 下午5:31:26
 * @version V1.0.0
 * description：
 * 
 */
public interface BaseUrl {

	/***************************** 用户模块  **********************************/
	String UPLOAD = "/upload"; //上传图片
	
	/***************************** 用户模块  **********************************/
	String USER_LOGIN = "/user/login"; //用户登录
	String USER_HOME = "/user/home"; //用户首页
	String USER_LIST = "/user/list";   //用户列表
	String USER_VIEW = "/user/view/{id}"; //查看用户
	String USER_ADD = "/user/add"; //新增用户
	String USER_EDIT = "/user/edit/{id}"; //编辑用户
	String USER_DELETE = "/user/delete/{id}"; //删除用户
	
	String USER_HEAD = "/user/head"; //用户登出
	String USER_LOGOUT = "/user/logout"; //用户登出
	String USER_UNAUTHORIZED = "/user/unauthorized"; //用户无权限
	
	/***************************** 角色模块  **********************************/
	String ROLE_LIST = "/role/list"; //角色列表
	String ROLE_ADD = "/role/add"; //新增角色
	String ROLE_EDIT = "/role/edit/{id}"; //编辑角色
	String ROLE_VIEW = "/role/view/{id}"; //查看角色
	String ROLE_DELETE = "/role/delete/{id}"; //删除角色
	
	/***************************** 权限模块  **********************************/
	String PERMISSION_LIST = "/permission/list"; //权限列表
	String PERMISSION_ADD = "/permission/add"; //新增权限
	String PERMISSION_EDIT = "/permission/edit/{id}"; //编辑权限
	String PERMISSION_VIEW = "/permission/view/{id}"; //查看权限
	String PERMISSION_DELETE = "/permission/delete/{id}"; //删除权限
	String PERMISSION_INIT_ADMIN = "/permission/initAdmin"; //权限初始化
	
	/***************************** 通知模块  **********************************/
	String NOTICE_LIST = "/notice/list"; //通知列表
	String NOTICE_ADD = "/notice/add"; //新增通知
	
}
