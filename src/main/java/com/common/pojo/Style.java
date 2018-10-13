package com.common.pojo;

import com.common.core.model.BaseObject;

public class Style extends BaseObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//数据ID
    private Long id;

    //风格名称
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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