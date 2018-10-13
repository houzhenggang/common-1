/*
 * @(#)GoodsCommand.java 2018年5月27日
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
 * @date 2018年5月27日 下午5:16:00
 * @version V1.0.0 description：
 * 
 */
public class GoodsCommand extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 商品名称
	private String name;

	// 商品关键词
	private String keywords;

	// 商品价格（单位：分）
	private Integer price;

	// 颜色分类
	private String colors;

	// 长度
	private String longness;

	// 材质
	private String material;

	//风格类型ID
    private Long styleId;

	// 环保等级
	private String envLevel;

	// 商品类型ID
	private Long categoryId;

	// 商品详情
	private String detail;

	// 商品详情图片
	private String detailImgs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getLongness() {
		return longness;
	}

	public void setLongness(String longness) {
		this.longness = longness;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	public String getEnvLevel() {
		return envLevel;
	}

	public void setEnvLevel(String envLevel) {
		this.envLevel = envLevel;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetailImgs() {
		return detailImgs;
	}

	public void setDetailImgs(String detailImgs) {
		this.detailImgs = detailImgs;
	}

}
