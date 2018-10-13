package com.common.dto;

import java.util.List;

import com.common.core.model.BaseObject;

/**
 *
 * @author zhaomingyang9
 * @date 2018年10月11日 下午3:04:08
 * @version V1.0.0
 * description：
 * 
 */
public class GoodsDTO extends BaseObject{

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
    
    //商品关键词列表
    private List<String> keywordList;

    //商品价格（单位：分）
    private Integer price;

    //颜色分类
    private String colors;
    
    //颜色分类列表
    private List<String> colorList;

    //长度
    private String longness;

    //材质
    private String material;

    //风格
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
    
    //商品详情图片列表
    private List<String> detailImgList;

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
		this.code = code;
	}

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

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
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

	public List<String> getColorList() {
		return colorList;
	}

	public void setColorList(List<String> colorList) {
		this.colorList = colorList;
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

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
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

	public List<String> getDetailImgList() {
		return detailImgList;
	}

	public void setDetailImgList(List<String> detailImgList) {
		this.detailImgList = detailImgList;
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
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
}
