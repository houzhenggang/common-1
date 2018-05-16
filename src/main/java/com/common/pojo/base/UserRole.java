/*
 * @(#) UserRoleKey.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo.base;

import com.common.core.model.BaseObject;

public class UserRole extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户ID
    private Long userId;

    //角色ID
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}