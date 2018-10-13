/*
 * @(#)StyleManageServiceImpl.java 2018年5月27日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.StyleCommand;
import com.common.dao.StyleDAO;
import com.common.pojo.Style;
import com.common.service.manage.StyleManageService;
import com.common.util.NewDate;

/**
 *
 * @author Administrator
 * @date 2018年5月27日 下午10:18:19
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class StyleManageServiceImpl implements StyleManageService {

	@Autowired
	private StyleDAO styleDAO;
	
	@Override
	public Style getStyle(Long id) {
		return styleDAO.selectByPrimaryKey(id);
	}

	@Override
	public void createStyle(StyleCommand styleCommand, String creator) {
		Style style = new Style();
		style.setName(styleCommand.getName());
		style.setCreator(creator);
		style.setCreateTime(NewDate.getDateTime());
		style.setUpdateTime(style.getCreateTime());
		styleDAO.insertSelective(style);
	}

	@Override
	public void deleteStyle(Long id) {
		styleDAO.deleteByPrimaryKey(id);
	}

	@Override
	public void updateStyle(Style style) {
		styleDAO.updateByPrimaryKey(style);
	}

	@Override
	public void updateStyle(Style style, StyleCommand styleCommand) {
		style.setName(styleCommand.getName());
		style.setUpdateTime(NewDate.getDateTime());
		styleDAO.updateByPrimaryKeySelective(style);
	}

	@Override
	public List<Style> getAllStyle() {
		return styleDAO.getAllStyle();
	}
	
}
