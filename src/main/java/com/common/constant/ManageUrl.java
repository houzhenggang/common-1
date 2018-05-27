/*
 * @(#)ManageUrl.java 2018年5月16日
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
public interface ManageUrl {
	
	/***************************** 公告模块  **********************************/
	String NOTICE_LIST = "/notice/list"; //公告通知列表
	String NOTICE_ADD = "/notice/add"; //新增公告通知
	String NOTICE_EDIT = "/notice/edit/{id}"; //修改公告通知
	String NOTICE_VIEW = "/notice/view/{id}"; //查看公告通知
	String NOTICE_DELETE = "/notice/delete/{id}"; //删除公告通知
	String NOTICE_CHANGE = "/notice/change/{id}"; //改变公告状态
	
	/***************************** 租户模块  **********************************/
	String TENANT_LIST = "/tenant/list"; //租户列表
	String TENANT_ADD = "/tenant/add"; //新增租户
	String TENANT_EDIT = "/tenant/edit/{id}"; //修改租户
	String TENANT_VIEW = "/tenant/view/{id}"; //查看租户
	String TENANT_DELETE = "/tenant/delete/{id}"; //删除租户
	String TENANT_CHANGE = "/tenant/change/{id}"; //冻结/解冻租户
	
	/***************************** 商品模块  **********************************/
	String GOODS_LIST = "/goods/list"; //商品列表
	String GOODS_ADD = "/goods/add"; //新增商品
	String GOODS_EDIT = "/goods/edit/{id}"; //修改商品
	String GOODS_VIEW = "/goods/view/{id}"; //查看商品
	String GOODS_DELETE = "/goods/delete/{id}"; //删除商品
	String GOODS_CHANGE = "/goods/change/{id}"; //上架/下架商品
	
}
