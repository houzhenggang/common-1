/*
 * @(#)CategoryServiceImpl.java 2018年5月18日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.core.dto.ResponseDTO;
import com.common.dao.CategoryDAO;
import com.common.dto.CategoryDTO;
import com.common.service.app.CategoryService;

/**
 *
 * @author Administrator
 * @date 2018年5月18日 下午8:21:05
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public ResponseDTO<List<CategoryDTO>> selectAll() {
		ResponseDTO<List<CategoryDTO>> response = new ResponseDTO<List<CategoryDTO>>();
		List<CategoryDTO> list = categoryDAO.selectAll();
		response.setData(list);
		return response;
	}

}
