/*
 * @(#)PermissionCommand.java 2018年5月19日
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
 * @date 2018年5月19日 下午8:38:21
 * @version V1.0.0
 * description：
 * 
 */
public class PermissionCommand extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//数据ID
    private Integer id;

    //父ID
    private Integer parentid;

    //权限名称
    private String name;

    //权限表达式
    private String element;

    //权限描述
    private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
