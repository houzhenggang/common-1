/*
 * @(#)UserAddressService.java 2018年5月16日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service;

import com.common.core.dto.ResponseDTO;
import com.common.dto.UserAddressDTO;
import com.common.pojo.UserAddress;

/**
 *
 * @author Administrator
 * @date 2018年5月16日 上午11:48:21
 * @version V1.0.0
 * description：
 * 
 */
public interface UserAddressService {

	ResponseDTO<UserAddress> selectById(UserAddressDTO dto);
	
	ResponseDTO<Integer> add(UserAddressDTO dto);
	
	ResponseDTO<Integer> update(UserAddressDTO dto);
	
	ResponseDTO<Integer> delete(UserAddressDTO dto);
	
}
