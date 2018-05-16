/*
 * @(#)UserAddressServiceImpl.java 2018年5月16日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.constant.ErrorCode;
import com.common.core.constant.ResponseStatus;
import com.common.core.dto.ResponseDTO;
import com.common.dao.UserAddressDAO;
import com.common.dto.UserAddressDTO;
import com.common.pojo.UserAddress;
import com.common.service.UserAddressService;
import com.common.util.Assert;
import com.common.util.ResponseUtils;

/**
 *
 * @author Administrator
 * @date 2018年5月16日 上午11:49:48
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

	@Autowired
	private UserAddressDAO userAddressDAO;
	
	@Override
	public ResponseDTO<UserAddress> selectById(UserAddressDTO dto) {
		ResponseDTO<UserAddress> response = new ResponseDTO<UserAddress>();
		Assert.notNull(dto, ErrorCode.PARAMETER_NULL);
		Assert.notNull(dto.getId(), "用户地址ID不能为空");
		UserAddress userAddress = userAddressDAO.selectByPrimaryKey(dto.getId());
		if (userAddress == null) {
            response.setStatus(ResponseStatus.FAIL.getCode());
            response.setMessage("找不到对应的用户地址");
            return response; 
        }
		response.setData(userAddress);
		return response;
	}

	@Override
	public ResponseDTO<Integer> add(UserAddressDTO dto) {
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		Assert.notNull(dto, ErrorCode.PARAMETER_NULL);
		//Assert.notNull(dto.getToken(), "用户token不能为空");
		Assert.notNull(dto.getProvinceId(), "省份ID不能为空");
		Assert.notNull(dto.getCityId(), "城市ID不能为空");
		Assert.notNull(dto.getDistrictId(), "地区ID不能为空");
		Assert.notNull(dto.getLinkMan(), "联系人不能为空");
		Assert.notNull(dto.getMobile(), "联系电话不能为空");
		
		UserAddress userAddress = new UserAddress();
		userAddress.setToken(dto.getToken());
		userAddress.setProvinceId(dto.getProvinceId());
		userAddress.setProvinceName(dto.getProvinceName());
		userAddress.setCityId(dto.getCityId());
		userAddress.setCityName(dto.getCityName());
		userAddress.setDistrictId(dto.getDistrictId());
		userAddress.setDistrictName(dto.getDistrictName());
		userAddress.setAddress(dto.getAddress());
		userAddress.setLinkMan(dto.getLinkMan());
		userAddress.setMobile(dto.getMobile());
		userAddress.setCode(dto.getCode());
		userAddress.setIsDefault(dto.getIsDefault());
		
		int result = userAddressDAO.insertSelective(userAddress);
		if (result != 1) {
            ResponseUtils.failedWithMsg(response, "[系统内部错误]--新增用户地址错误");
        }
		return response;
	}

	@Override
	public ResponseDTO<Integer> update(UserAddressDTO dto) {
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		Assert.notNull(dto, ErrorCode.PARAMETER_NULL);
		//Assert.notNull(dto.getToken(), "用户token不能为空");
		Assert.notNull(dto.getId(), "用户地址ID不能为空");
		Assert.notNull(dto.getProvinceId(), "省份ID不能为空");
		Assert.notNull(dto.getCityId(), "城市ID不能为空");
		Assert.notNull(dto.getDistrictId(), "地区ID不能为空");
		Assert.notNull(dto.getLinkMan(), "联系人不能为空");
		Assert.notNull(dto.getMobile(), "联系电话不能为空");
		
		UserAddress userAddress = userAddressDAO.selectByPrimaryKey(dto.getId());
		userAddress.setToken(dto.getToken());
		userAddress.setProvinceId(dto.getProvinceId());
		userAddress.setProvinceName(dto.getProvinceName());
		userAddress.setCityId(dto.getCityId());
		userAddress.setCityName(dto.getCityName());
		userAddress.setDistrictId(dto.getDistrictId());
		userAddress.setDistrictName(dto.getDistrictName());
		userAddress.setAddress(dto.getAddress());
		userAddress.setLinkMan(dto.getLinkMan());
		userAddress.setMobile(dto.getMobile());
		userAddress.setCode(dto.getCode());
		userAddress.setIsDefault(dto.getIsDefault());
		
		int result = userAddressDAO.updateByPrimaryKeySelective(userAddress);
		if (result != 1) {
            ResponseUtils.failedWithMsg(response, "[系统内部错误]--修改用户地址错误");
        }
		return response;
	}

	@Override
	public ResponseDTO<Integer> delete(UserAddressDTO dto) {
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		Assert.notNull(dto, ErrorCode.PARAMETER_NULL);
		//Assert.notNull(dto.getToken(), "用户token不能为空");
		Assert.notNull(dto.getId(), "用户地址ID不能为空");
		UserAddress userAddress = userAddressDAO.selectByPrimaryKey(dto.getId());
		if (userAddress == null) {
            response.setStatus(ResponseStatus.FAIL.getCode());
            response.setMessage("找不到对应的用户地址");
            return response; 
        }
		int result = userAddressDAO.deleteByPrimaryKey(dto.getId());
		if (result != 1) {
            ResponseUtils.failedWithMsg(response, "[系统内部错误]--删除用户地址错误");
        }
		return response;
	}

}
