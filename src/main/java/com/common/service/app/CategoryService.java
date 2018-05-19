/*
 * @(#)CategoryService.java 2018年5月18日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.app;

import java.util.List;

import com.common.core.dto.ResponseDTO;
import com.common.dto.CategoryDTO;

/**
 *
 * @author Administrator
 * @date 2018年5月18日 下午8:10:45
 * @version V1.0.0
 * description：
 * 
 */
public interface CategoryService {

	ResponseDTO<List<CategoryDTO>> selectAll();
	
}
