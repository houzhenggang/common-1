/*
 * @(#) Role.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo.base;

import com.common.core.model.BaseObject;

public class Role extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//据数ID
    private Long id;

    //角色名称
    private String name;

    //角色描述
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}