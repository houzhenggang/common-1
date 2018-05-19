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
public interface AppUrl {

	/***************************** 用户地址  **********************************/
	String USER_ADDRESS_DETAIL = "/userAddress/selectById"; //用户地址详情
	String USER_ADDRESS_ADD = "/userAddress/add"; //用户地址新增
	String USER_ADDRESS_UPDATE = "/userAddress/update"; //用户地址修改
	String USER_ADDRESS_DELETE = "/userAddress/delete"; //用户地址删除
	
	/***************************** 类型  **********************************/
	String CATEGORY_SELECT_ALL = "/category/selectAll"; //查询所有类型
	
	
}
