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

import com.common.command.CategoryCommand;
import com.common.dao.CategoryDAO;
import com.common.pojo.Category;
import com.common.service.manage.CategoryManageService;
import com.common.util.NewDate;

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
	public Category getCategory(Long id) {
		return categoryDAO.selectByPrimaryKey(id);
	}

	@Override
	public void createCategory(CategoryCommand categoryCommand, String creator) {
		Category category = new Category();
		category.setName(categoryCommand.getName());
		category.setCreator(creator);
		category.setCreateTime(NewDate.getDateTime());
		category.setUpdateTime(category.getCreateTime());
		categoryDAO.insertSelective(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryDAO.deleteByPrimaryKey(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDAO.updateByPrimaryKey(category);
	}

	@Override
	public void updateCategory(Category category, CategoryCommand categoryCommand) {
		category.setName(categoryCommand.getName());
		category.setUpdateTime(NewDate.getDateTime());
		categoryDAO.updateByPrimaryKeySelective(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}
	
}
