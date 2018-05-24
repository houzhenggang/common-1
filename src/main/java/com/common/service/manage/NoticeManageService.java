/*
 * @(#)NoticeService.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.NoticeCommand;
import com.common.pojo.Notice;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午3:22:41
 * @version V1.0.0
 * description：
 * 
 */
public interface NoticeManageService {

	Notice getNotice(Long id);

    void createNotice(NoticeCommand noticeCommand, String creator, String imgPath);

    void deleteNotice(Long id);

    void updateNotice(Notice notice);
    
    void updateNotice(Notice notice, NoticeCommand noticeCommand);
    
    List<Notice> getAllNotice();
	
}
