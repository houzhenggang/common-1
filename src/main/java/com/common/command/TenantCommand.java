/*
 * @(#)TenantCommand.java 2018年5月25日
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
 * @date 2018年5月25日 上午11:13:56
 * @version V1.0.0
 * description：
 * 
 */
public class TenantCommand extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//租户名称
    private String name;

    //租户年龄
    private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
