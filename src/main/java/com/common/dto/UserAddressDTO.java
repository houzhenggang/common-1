/*
 * @(#)UserAddressDTO.java 2018年5月16日
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
 * @date 2018年5月16日 下午3:25:47
 * @version V1.0.0 description：
 * 
 */
public class UserAddressDTO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 数据自增ID
	private Long id;

	// 用户token
	private String token;

	// 省份id
	private Integer provinceId;

	// 省份名称
	private String provinceName;

	// 城市id
	private Integer cityId;

	// 城市名称
	private String cityName;

	// 地区id
	private Integer districtId;

	// 地区名称
	private String districtName;

	// 详细地址
	private String address;

	// 联系人
	private String linkMan;

	// 联系电话
	private String mobile;

	// 邮政编码
	private String code;

	// 是否默认 1是 0否
	private Integer isDefault;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
