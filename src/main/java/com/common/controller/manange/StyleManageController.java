/*
 * @(#)StyleManageController.java 2018年10月13日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.manange;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.command.StyleCommand;
import com.common.constant.ManageUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.Style;
import com.common.service.base.UserService;
import com.common.service.manage.StyleManageService;

/**
 *
 * @author Administrator
 * @date 2018年10月13日 下午4:11:16
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/manage")
public class StyleManageController extends BaseController{

	@Autowired
	private StyleManageService styleManageService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 风格类型列表
	 * @author Administrator
	 * @date 2018年10月13日 下午4:17:02
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping(value = ManageUrl.STYLE_LIST)
    //@RequiresPermissions("notice:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Style> listStyle = styleManageService.getAllStyle();
    	HttpSession session = request.getSession();
    	session.setAttribute("listStyle", listStyle);
    	return "style/list";
    }
	
    /**
     * 新增风格类型页面
     * @author Administrator
     * @date 2018年10月13日 下午4:16:47
     * @param model
     * @param styleCommand
     * @return
     */
    @RequestMapping(value = ManageUrl.STYLE_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute StyleCommand styleCommand){
		return "style/add";
	}
    
    /**
     * 新增风格类型
     * @author Administrator
     * @date 2018年10月13日 下午4:22:33
     * @param model
     * @param styleCommand
     * @return
     */
    @RequestMapping(value = ManageUrl.STYLE_ADD, method = RequestMethod.POST)
    //@RequiresPermissions("banner:add")
    public String add(Model model, @ModelAttribute StyleCommand styleCommand){
    	if (styleCommand.getName() == null || "".equals(styleCommand.getName())) {
    		model.addAttribute("error", "请输入风格类型名称！");
    		return addForm(model, styleCommand);
    	}
    	String creator = userService.getCurrentUser().getUsername();
    	styleManageService.createStyle(styleCommand, creator);
    	return "redirect:/manage/style/list";
    }
    
    /**
     * 修改风格类型页面
     * @author Administrator
     * @date 2018年10月13日 下午4:24:52
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.STYLE_EDIT, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String editForm(Model model, @PathVariable Long id){
		Style style = styleManageService.getStyle(id);
		if (style == null) {
			return "redirect:/manage/style/list";
		}
		model.addAttribute("id", id);
		model.addAttribute("style", style);
		return "style/edit";
	}
    
	/**
	 * 修改风格类型
	 * @author Administrator
	 * @date 2018年10月13日 下午4:26:40
	 * @param model
	 * @param id
	 * @param styleCommand
	 * @return
	 */
	@RequestMapping(value = ManageUrl.STYLE_EDIT, method = RequestMethod.POST)
    //@RequiresPermissions("banner:edit")
	public String edit(Model model, @PathVariable Long id, @ModelAttribute StyleCommand styleCommand){
		if (styleCommand.getName() == null || "".equals(styleCommand.getName())) {
    		model.addAttribute("error", "风格类型名称不能为空，已恢复至修改之前状态！！");
    		return editForm(model,id);
    	}
		
		Style style = styleManageService.getStyle(id);
		if (style == null) {
			return "redirect:/manage/style/list";
		}
		
		style.setCreator(userService.getCurrentUser().getUsername());
		
		styleManageService.updateStyle(style, styleCommand);
		return "redirect:/manage/style/list";
	}    

	/**
	 * 查看风格类型
	 * @author Administrator
	 * @date 2018年10月13日 下午5:25:34
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = ManageUrl.STYLE_VIEW, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String view(Model model, @PathVariable Long id){
		Style style = styleManageService.getStyle(id);
		if (style == null) {
			return "redirect:/manage/style/list";
		}
		model.addAttribute("style", style);
		return "style/view";
	}
	
	/**
	 * 删除风格类型
	 * @author Administrator
	 * @date 2018年10月13日 下午4:27:57
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = ManageUrl.STYLE_DELETE, method = RequestMethod.GET)
	//@RequiresPermissions("banner:delete")
	public String delete(Model model, @PathVariable Long id){
		Style style = styleManageService.getStyle(id);
		if (style == null) {
			return "redirect:/manage/style/list";
		}
		styleManageService.deleteStyle(id);
		return "redirect:/manage/style/list";
	}	
}
