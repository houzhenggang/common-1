/*
 * @(#) Goods.java 2018年10月13日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo;

import com.common.core.model.BaseObject;

public class Goods extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//数据ID
    private Long id;

    //商品编号
    private String code;

    //商品名称
    private String name;

    //商品关键词
    private String keywords;

    //商品价格（单位：分）
    private Integer price;

    //颜色分类
    private String colors;

    //长度
    private String longness;

    //材质
    private String material;

    //风格类型ID
    private Long styleId;

    //环保等级
    private String envLevel;

    //商品类型ID
    private Long categoryId;

    //商品封面图片
    private String coverImg;

    //商品详情
    private String detail;

    //商品详情图片
    private String detailImgs;

    //商品状态 1：上架 0：下架
    private Integer status;

    //创建者
    private String creator;

    //创建时间
    private String createTime;

    //修改时间
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
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
        this.colors = colors == null ? null : colors.trim();
    }

    public String getLongness() {
        return longness;
    }

    public void setLongness(String longness) {
        this.longness = longness == null ? null : longness.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
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
        this.envLevel = envLevel == null ? null : envLevel.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getDetailImgs() {
        return detailImgs;
    }

    public void setDetailImgs(String detailImgs) {
        this.detailImgs = detailImgs == null ? null : detailImgs.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}