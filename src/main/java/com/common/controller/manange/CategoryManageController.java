/*
 * @(#)CategoryManageController.java 2018年10月13日
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

import com.common.command.CategoryCommand;
import com.common.constant.ManageUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.Category;
import com.common.service.base.UserService;
import com.common.service.manage.CategoryManageService;

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
public class CategoryManageController extends BaseController{

	@Autowired
	private CategoryManageService categoryManageService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 商品类别列表
	 * @author Administrator
	 * @date 2018年10月13日 下午4:17:02
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping(value = ManageUrl.CATEGORY_LIST)
    //@RequiresPermissions("notice:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Category> listCategory = categoryManageService.getAllCategory();
    	HttpSession session = request.getSession();
    	session.setAttribute("listCategory", listCategory);
    	return "category/list";
    }
	
    /**
     * 新增商品类别页面
     * @author Administrator
     * @date 2018年10月13日 下午4:16:47
     * @param model
     * @param categoryCommand
     * @return
     */
    @RequestMapping(value = ManageUrl.CATEGORY_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute CategoryCommand categoryCommand){
		return "category/add";
	}
    
    /**
     * 新增商品类别
     * @author Administrator
     * @date 2018年10月13日 下午4:22:33
     * @param model
     * @param categoryCommand
     * @return
     */
    @RequestMapping(value = ManageUrl.CATEGORY_ADD, method = RequestMethod.POST)
    //@RequiresPermissions("banner:add")
    public String add(Model model, @ModelAttribute CategoryCommand categoryCommand){
    	if (categoryCommand.getName() == null || "".equals(categoryCommand.getName())) {
    		model.addAttribute("error", "请输入商品类别名称！");
    		return addForm(model, categoryCommand);
    	}
    	String creator = userService.getCurrentUser().getUsername();
    	categoryManageService.createCategory(categoryCommand, creator);
    	return "redirect:/manage/category/list";
    }
    
    /**
     * 修改商品类别页面
     * @author Administrator
     * @date 2018年10月13日 下午4:24:52
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.CATEGORY_EDIT, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String editForm(Model model, @PathVariable Long id){
		Category category = categoryManageService.getCategory(id);
		if (category == null) {
			return "redirect:/manage/category/list";
		}
		model.addAttribute("id", id);
		model.addAttribute("category", category);
		return "category/edit";
	}
    
	/**
	 * 修改商品类别
	 * @author Administrator
	 * @date 2018年10月13日 下午4:26:40
	 * @param model
	 * @param id
	 * @param categoryCommand
	 * @return
	 */
	@RequestMapping(value = ManageUrl.CATEGORY_EDIT, method = RequestMethod.POST)
    //@RequiresPermissions("banner:edit")
	public String edit(Model model, @PathVariable Long id, @ModelAttribute CategoryCommand categoryCommand){
		if (categoryCommand.getName() == null || "".equals(categoryCommand.getName())) {
    		model.addAttribute("error", "商品类别名称不能为空，已恢复至修改之前状态！！");
    		return editForm(model,id);
    	}
		
		Category category = categoryManageService.getCategory(id);
		if (category == null) {
			return "redirect:/manage/category/list";
		}
		
		category.setCreator(userService.getCurrentUser().getUsername());
		
		categoryManageService.updateCategory(category, categoryCommand);
		return "redirect:/manage/category/list";
	}    

	/**
	 * 查看商品类别
	 * @author Administrator
	 * @date 2018年10月13日 下午5:25:34
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = ManageUrl.CATEGORY_VIEW, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String view(Model model, @PathVariable Long id){
		Category category = categoryManageService.getCategory(id);
		if (category == null) {
			return "redirect:/manage/category/list";
		}
		model.addAttribute("category", category);
		return "category/view";
	}
	
	/**
	 * 删除商品类别
	 * @author Administrator
	 * @date 2018年10月13日 下午4:27:57
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = ManageUrl.CATEGORY_DELETE, method = RequestMethod.GET)
	//@RequiresPermissions("banner:delete")
	public String delete(Model model, @PathVariable Long id){
		Category category = categoryManageService.getCategory(id);
		if (category == null) {
			return "redirect:/manage/category/list";
		}
		categoryManageService.deleteCategory(id);
		return "redirect:/manage/category/list";
	}	
}
