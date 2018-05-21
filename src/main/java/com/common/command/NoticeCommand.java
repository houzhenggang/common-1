/*
 * @(#)NoticeCommand.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.command;

import com.common.core.model.BaseObject;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午5:05:28
 * @version V1.0.0 description：
 * 
 */
public class NoticeCommand extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 公告标题
	private String title;

	// 公告内容
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
