/*
 * @(#)NoticeManageController.java 2018年5月23日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.manange;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.command.NoticeCommand;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.Notice;
import com.common.pojo.base.Permission;
import com.common.service.base.UserService;
import com.common.service.manage.NoticeManageService;

/**
 *
 * @author Administrator
 * @date 2018年5月23日 下午3:33:04
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/manage")
public class NoticeManageController extends BaseController{

	@Autowired
	private NoticeManageService noticeManageService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 公告列表
	 * @param model
	 * @param request
	 */
    @RequestMapping(value = BaseUrl.NOTICE_LIST)
    @RequiresPermissions("notice:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Notice> listNotice = noticeManageService.getAllNotice();
    	HttpSession session = request.getSession();
    	session.setAttribute("listNotice", listNotice);
    	return "notice/list";
    }
	
    /**
     * 新增公告页面
     * @param model
     * @param command
     * @return
     */
	@RequestMapping(value = BaseUrl.NOTICE_ADD, method = RequestMethod.GET)
    @RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute NoticeCommand noticeCommand){
		return "notice/add";
	}
	
    /**
     * 新增公告
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = BaseUrl.NOTICE_ADD, method = RequestMethod.POST)
    @RequiresPermissions("banner:add")
    public String add(Model model, @ModelAttribute NoticeCommand noticeCommand){
    	if(noticeCommand.getTitle() == null || "".equals(noticeCommand.getTitle())){
    		model.addAttribute("error", "请输入公告标题！");
    		return addForm(model, noticeCommand);
    	}
    	noticeManageService.createNotice(noticeCommand, userService.getCurrentUser().getUsername());
    	return "redirect:/base/notice/list";
    }
	
}
