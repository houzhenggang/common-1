/*
 * @(#)CategoryDTO.java 2018年5月18日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.dto;

import com.common.core.model.BaseObject;

/**
 *
 * @author Administrator
 * @date 2018年5月18日 下午8:19:35
 * @version V1.0.0 description：
 * 
 */
public class CategoryDTO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 数据ID
	private Long id;

	// 类别名称
	private String name;

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
		this.name = name;
	}

}
