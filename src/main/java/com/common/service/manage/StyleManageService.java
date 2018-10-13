/*
 * @(#)StyleService.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.StyleCommand;
import com.common.pojo.Style;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午3:21:33
 * @version V1.0.0
 * description：
 * 
 */
public interface StyleManageService {

	Style getStyle(Long id);

    void createStyle(StyleCommand styleCommand, String creator);

    void deleteStyle(Long id);

    void updateStyle(Style notice);
    
    void updateStyle(Style notice, StyleCommand styleCommand);
    
    List<Style> getAllStyle();
	
}
