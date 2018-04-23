/*
 * @(#) RolePermission.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo;

import com.common.core.model.BaseObject;

public class RolePermissions extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//角色ID
    private Long rolesId;

    //权限表达式
    private String element;

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element == null ? null : element.trim();
    }
}