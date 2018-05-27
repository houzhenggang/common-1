/*
 * @(#)CategoryManageServiceImpl.java 2018年5月27日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CategoryDAO;
import com.common.pojo.Category;
import com.common.service.manage.CategoryManageService;

/**
 *
 * @author Administrator
 * @date 2018年5月27日 下午10:18:19
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class CategoryManageServiceImpl implements CategoryManageService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}

}
