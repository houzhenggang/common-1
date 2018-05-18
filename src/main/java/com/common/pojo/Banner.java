/*
 * @(#) Banner.java 2018年05月18日
 *
 * Copyright (c), 2016 深圳市万睿智能科技有限公司（Shenzhen Wan Rui Intelligent Technology Co., Ltd.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.pojo;

import com.common.core.model.BaseObject;

public class Banner extends BaseObject  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//数据自增ID
    private Long id;

    //关联商品ID
    private Long goodsId;

    //关联商品编码
    private String goodsCode;

    //关联商品名称
    private String goodsName;

    //banner图片地址
    private String imgPath;

    //Banner状态 0：不轮转 1：轮转中
    private Integer status;

    //创建者
    private String creator;

    //创建时间
    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
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
}