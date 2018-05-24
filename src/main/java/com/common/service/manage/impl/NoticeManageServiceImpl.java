/*
 * @(#)NoticeServiceImpl.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.NoticeCommand;
import com.common.constant.StatusCode;
import com.common.dao.NoticeDAO;
import com.common.pojo.Notice;
import com.common.service.manage.NoticeManageService;
import com.common.util.NewDate;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午5:15:10
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class NoticeManageServiceImpl implements NoticeManageService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public Notice getNotice(Long id) {
		return noticeDAO.selectByPrimaryKey(id);
	}

	@Override
	public void createNotice(NoticeCommand noticeCommand, String creator, String imgPath) {
		Notice notice = new Notice();
		notice.setTitle(noticeCommand.getTitle());
		notice.setContent(noticeCommand.getContent());
		notice.setImgPath(imgPath);
		notice.setStatus(StatusCode.STATUS_OFF.getCode());
		notice.setCreator(creator);
		notice.setCreateTime(NewDate.getDateTime());
		notice.setUpdateTime(notice.getCreateTime());
		noticeDAO.insertSelective(notice);
	}

	@Override
	public void deleteNotice(Long id) {
		noticeDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateNotice(Notice notice) {
		noticeDAO.updateByPrimaryKeySelective(notice);		
	}

	@Override
	public void updateNotice(Notice notice, NoticeCommand noticeCommand) {
		notice.setTitle(noticeCommand.getTitle());
		notice.setContent(noticeCommand.getContent());
		notice.setStatus(StatusCode.STATUS_OFF.getCode());
		notice.setUpdateTime(NewDate.getDateTime());
		noticeDAO.updateByPrimaryKeySelective(notice);
	}

	@Override
	public List<Notice> getAllNotice() {
		return noticeDAO.getAllNotice();
	}

}
