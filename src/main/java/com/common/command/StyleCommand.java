/*
 * @(#)CategoryCommand.java 2018年10月13日
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
 * @date 2018年10月13日 下午3:58:19
 * @version V1.0.0
 * description：
 * 
 */
public class StyleCommand extends BaseObject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//风格名称
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
