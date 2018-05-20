/*
 * @(#)PermissionController.java 2018年5月20日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.base;

import java.util.HashSet;
import java.util.List;

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

import com.common.command.PermissionCommand;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.base.Permission;
import com.common.pojo.base.User;
import com.common.service.base.PermissionService;
import com.common.service.base.UserService;

/**
 *
 * @author Administrator
 * @date 2018年5月20日 下午9:14:59
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/base")
public class PermissionController extends BaseController{

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 权限列表
	 * @param model
	 * @param request
	 */
    @RequestMapping(value = BaseUrl.PERMISSION_LIST)
    @RequiresPermissions("permission:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Permission> listPermission = permissionService.getAllPermission();
    	HttpSession session = request.getSession();
    	session.setAttribute("listPermission", listPermission);
    	return "permission/list";
    }
	
    /**
     * 新增权限页面
     * @param model
     * @param permissionCommand
     * @return
     */
	@RequestMapping(value = BaseUrl.PERMISSION_ADD, method = RequestMethod.GET)
    @RequiresPermissions("permission:add")
	public String addForm(Model model, @ModelAttribute PermissionCommand permissionCommand){
		List<Permission> list = permissionService.getParentPermission();
		model.addAttribute("list", list);
		return "permission/add";
	}
	
    /**
     * 新增权限
     * @param model
     * @param permissionCommand
     * @return
     */
    @RequestMapping(value = BaseUrl.PERMISSION_ADD, method = RequestMethod.POST)
    @RequiresPermissions("permission:add")
    public String add(Model model, @ModelAttribute PermissionCommand permissionCommand){
    	logger.info("调用[权限新增接口]服务WEB后台接口,入参：{}", permissionCommand);
    	if(permissionCommand.getName() == null || "".equals(permissionCommand.getName())){
    		model.addAttribute("error", "请输入权限名称！");
    		return addForm(model, permissionCommand);
    	}
    	if(permissionCommand.getElement() == null || "".equals(permissionCommand.getElement())){
    		model.addAttribute("error", "请输入权限表达方式！");
    		return addForm(model, permissionCommand);
    	}
    	
    	permissionService.createPermission(permissionCommand);
    	return "redirect:/base/permission/list";
    }
    
    /**
     * 修改权限页面
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = BaseUrl.PERMISSION_EDIT, method = RequestMethod.GET)
    @RequiresPermissions("permission:edit")
	public String editForm(Model model, @PathVariable int id){
		Permission permission = permissionService.getPermission(id);
		List<Permission> list = permissionService.getParentPermission();
		model.addAttribute("id", id);
		model.addAttribute("list", list);
		model.addAttribute("permission", permission);
		return "permission/edit";
	}
    
	/**
	 * 修改权限
	 * @param model
	 * @param id
	 * @param command
	 * @return
	 */
	@RequestMapping(value = BaseUrl.PERMISSION_EDIT, method = RequestMethod.POST)
    @RequiresPermissions("permission:edit")
	public String edit(Model model, @PathVariable int id, @ModelAttribute PermissionCommand permissionCommand){
		logger.info("调用[权限修改接口]服务WEB后台接口,入参id：{}，参数为：{}", id, permissionCommand);
		if(permissionCommand.getName() == null || "".equals(permissionCommand.getName())){
    		model.addAttribute("error", "权限名称不能为空，已恢复至修改之前状态！");
    		return editForm(model,id);
    	}
    	if(permissionCommand.getElement() == null || "".equals(permissionCommand.getElement())){
    		model.addAttribute("error", "权限表达式不能为空，已恢复至修改之前状态！");
    		return editForm(model,id);
    	}
		Permission permission = permissionService.getPermission(id);
		permissionService.updatePermission(permission, permissionCommand);
		return "redirect:/base/permission/list";
	}
	
	 /**
	  * 查看权限
	  * @param model
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = BaseUrl.PERMISSION_VIEW, method = RequestMethod.GET)
	 @RequiresPermissions("permission:view")
	 public String view(Model model, @PathVariable int id){
		Permission permission = permissionService.getPermission(id);
		List<Permission> list = permissionService.getParentPermission();
		model.addAttribute("list", list);
		model.addAttribute("permission", permission);
		return "permission/view";
	 }
	
	 /**
	  * 删除权限
	  * @param model
	  * @param id
	  * @return
	  */
	@RequestMapping(value = BaseUrl.PERMISSION_DELETE, method = RequestMethod.GET)
	@RequiresPermissions("permission:delete")
	public String delete(Model model, @PathVariable int id){
		permissionService.deletePermission(id);
		return "redirect:/base/permission/list";
	}
	 
	/**
	 * 系统管理员权限初始化
	 * @param model
	 * @return
	 */
	@RequestMapping(value = BaseUrl.PERMISSION_INIT_ADMIN, method = RequestMethod.GET)
	@RequiresPermissions("permission:initAdmin")
	public String initAdmin(Model model){
		User user = userService.getCurrentUser();
		List<Permission> plist = permissionService.getAllPermission();
    	HashSet<String> allRolePermissionSet = new HashSet<String>();
    	for (Permission p : plist) {
    		allRolePermissionSet.add(p.getElement());
		}
    	permissionService.initAdminRole(user, allRolePermissionSet);
    	model.addAttribute("msg","初始化提示：系统管理员权限初始化成功！");
		return "permission/list";
	}
    
    
}
