/*
 * @(#) UsersRoleKey.java 2018年04月23日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo;

import com.common.core.model.BaseObject;

public class UsersRole extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户ID
    private Long usersId;

    //角色ID
    private Long rolesId;

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }
}