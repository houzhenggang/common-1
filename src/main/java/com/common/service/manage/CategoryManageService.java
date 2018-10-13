/*
 * @(#)CategoryService.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.CategoryCommand;
import com.common.pojo.Category;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午3:21:33
 * @version V1.0.0
 * description：
 * 
 */
public interface CategoryManageService {
	
	Category getCategory(Long id);

    void createCategory(CategoryCommand categoryCommand, String creator);

    void deleteCategory(Long id);

    void updateCategory(Category notice);
    
    void updateCategory(Category notice, CategoryCommand categoryCommand);
	
    List<Category> getAllCategory();
}
