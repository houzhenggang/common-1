/*
 * @(#)NoticeManageController.java 2018年5月23日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.manange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.command.NoticeCommand;
import com.common.constant.ManageUrl;
import com.common.constant.StatusCode;
import com.common.core.controller.BaseController;
import com.common.pojo.Notice;
import com.common.service.base.UserService;
import com.common.service.manage.NoticeManageService;
import com.common.util.NewDate;
import com.common.util.UploadHelper;

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
    @RequestMapping(value = ManageUrl.NOTICE_LIST)
    //@RequiresPermissions("notice:manage")
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
	@RequestMapping(value = ManageUrl.NOTICE_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute NoticeCommand noticeCommand){
		return "notice/add";
	}
	
    /**
     * 新增公告
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = ManageUrl.NOTICE_ADD, method = RequestMethod.POST)
    //@RequiresPermissions("banner:add")
    public String add(Model model, HttpServletRequest request, @ModelAttribute NoticeCommand noticeCommand){
    	if (noticeCommand.getTitle() == null || "".equals(noticeCommand.getTitle())) {
    		model.addAttribute("error", "请输入公告标题！");
    		return addForm(model, noticeCommand);
    	}
    	String imgPath = UploadHelper.uploadFile(request, "img", "notice");
    	String creator = userService.getCurrentUser().getUsername();
    	noticeManageService.createNotice(noticeCommand, creator, imgPath);
    	return "redirect:/manage/notice/list";
    }
	
    /**
     * 修改公告页面
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.NOTICE_EDIT, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String editForm(Model model, @PathVariable Long id){
		Notice notice = noticeManageService.getNotice(id);
		if (notice == null) {
			return "redirect:/manage/notice/list";
		}
		model.addAttribute("id", id);
		model.addAttribute("notice", notice);
		return "notice/edit";
	}
	
	/**
	 * 修改公告
	 * @param model
	 * @param request
	 * @param id
	 * @param noticeCommand
	 * @return
	 */
	@RequestMapping(value = ManageUrl.NOTICE_EDIT, method = RequestMethod.POST)
    //@RequiresPermissions("banner:edit")
	public String edit(Model model, HttpServletRequest request, @PathVariable Long id, 
			@ModelAttribute NoticeCommand noticeCommand){
		if (noticeCommand.getTitle() == null || "".equals(noticeCommand.getTitle())) {
    		model.addAttribute("error", "公告标题不能为空，已恢复至修改之前状态！！");
    		return editForm(model,id);
    	}
		
		Notice notice = noticeManageService.getNotice(id);
		if (notice == null) {
			return "redirect:/manage/notice/list";
		}
		
		String imgPath = UploadHelper.uploadFile(request, "img", "notice");
		if (imgPath != null && !"".equals(imgPath)) {
			notice.setImgPath(imgPath);
		}
		notice.setCreator(userService.getCurrentUser().getUsername());
		
		noticeManageService.updateNotice(notice, noticeCommand);
		return "redirect:/manage/notice/list";
	}    

	/**
     * 查看公告
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.NOTICE_VIEW, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String view(Model model, @PathVariable Long id){
		Notice notice = noticeManageService.getNotice(id);
		if (notice == null) {
			return "redirect:/manage/notice/list";
		}
		model.addAttribute("notice", notice);
		return "notice/view";
	}	
	
	 /**
	  * 删除公告
	  * @param model
	  * @param id
	  * @return
	  */
	@RequestMapping(value = ManageUrl.NOTICE_DELETE, method = RequestMethod.GET)
	//@RequiresPermissions("banner:delete")
	public String delete(Model model, @PathVariable Long id){
		Notice notice = noticeManageService.getNotice(id);
		if (notice == null) {
			return "redirect:/manage/notice/list";
		}
		noticeManageService.deleteNotice(id);
		return "redirect:/manage/notice/list";
	}
	
	/**
	 * 允许/不允许通知
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = ManageUrl.NOTICE_CHANGE, method = RequestMethod.POST)
	@ResponseBody
	//@RequiresPermissions("banner:change")
	public Object changeStatus(Model model, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Notice notice = noticeManageService.getNotice(id);
		if(notice != null){
	    	//状态【0：不滚动】
			if (notice.getStatus() == 0) {
				notice.setStatus(StatusCode.STATUS_ON.getCode());
				notice.setUpdateTime(NewDate.getDateTime());
				noticeManageService.updateNotice(notice);
			} else {
				notice.setStatus(StatusCode.STATUS_OFF.getCode());
				notice.setUpdateTime(NewDate.getDateTime());
				noticeManageService.updateNotice(notice);
			}
		}
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		return jsonObject;
		
	}
	
}
