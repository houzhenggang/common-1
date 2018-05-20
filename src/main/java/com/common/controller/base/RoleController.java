/*
 * @(#)RoleController.java 2018年5月20日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.base;

import java.util.ArrayList;
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

import com.common.command.RoleCommand;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.base.Permission;
import com.common.pojo.base.Role;
import com.common.pojo.base.User;
import com.common.service.base.PermissionService;
import com.common.service.base.RoleService;
import com.common.service.base.UserService;

/**
 *
 * @author Administrator
 * @date 2018年5月20日 下午5:38:09
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/base")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
    /**
     * 角色列表
     * @param model
     * @param request
     */
    @RequestMapping(value = BaseUrl.ROLE_LIST)
    @RequiresPermissions("role:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Role> listRole = roleService.getAllRole();
    	HttpSession session = request.getSession();
    	session.setAttribute("listRole", listRole);
    	return "role/list";
    }
	
	/**
	 * 新增角色页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = BaseUrl.ROLE_ADD, method = RequestMethod.GET)
    @RequiresPermissions("role:add")
    public String addForm(Model model, @ModelAttribute RoleCommand roleCommand) {
    	User currentUser = userService.getCurrentUser();
    	List<Permission> plist = permissionService.getAllPermission();
    	
    	//stripped simple permission string to a set to compare and mixed with current permission
    	HashSet<String> allRolePermissionSet = new HashSet<String>();
    	for (Permission p : plist) {
    		allRolePermissionSet.add(p.getElement());
		}
    	
    	//get all of permissions from current role that related with current user
    	HashSet<String> currentRolePermissionSet = new HashSet<String>();
    	List<Role> listCurrentRole = roleService.selectRolesByUserId(currentUser.getId());
    	List<String> currentList = new ArrayList<String>();
    	for (Role role : listCurrentRole) {
    		currentList = permissionService.selectPermissionsByRoleId(role.getId());
    		currentRolePermissionSet.addAll(currentList);
		}
    	
    	//compare and get mixed permissions
    	allRolePermissionSet.retainAll(currentRolePermissionSet);
    	
    	String jsonStr = "";
    	for (Permission permission : plist) {
    		if( allRolePermissionSet.contains(permission.getElement()) 
    				|| permission.getElement().contains(":-")){
	    		String tmp = "{'pid':"+"'"+permission.getParentid()+"'"+"," +
	    				"'input':"+"'"+permission.getElement()+"'"+"," +
	    				"'text':"+"'"+permission.getName()+"'"+"," +
	    				"'id':"+"'"+permission.getId()+"'"+"," +
	    				"'checked':"+"0" +
	    				"}";
	    		jsonStr += tmp + ","; 
    		}
		}
    	
    	//remove last comma in the end of the string
    	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
    	return "role/add";
    }

    /**
     * 新增角色
     * @param model
     * @param request
     * @param roleCommand
     * @return
     */
    @RequestMapping(value = BaseUrl.ROLE_ADD, method = RequestMethod.POST)
    @RequiresPermissions("role:add")
    public String add(Model model, @ModelAttribute RoleCommand roleCommand){
    	logger.info("调用[角色新增接口]服务WEB后台接口,入参：{}", roleCommand);
    	if(roleCommand.getName() == null || "".equals(roleCommand.getName().trim())){
    		model.addAttribute("error", "新增角色的角色名不能为空");
    		return addForm(model, roleCommand);
    	}
    	
    	roleService.createRole(roleCommand);
        return "redirect:/base/role/list";
    }
	
    /**
     * 修改角色页面
     * @param model
     * @param id
     * @param command
     * @return
     */
	@RequestMapping(value = BaseUrl.ROLE_EDIT, method= RequestMethod.GET)
    @RequiresPermissions("role:edit")
    public String editForm(Model model, @PathVariable Long id, @ModelAttribute RoleCommand roleCommand) {

		//系统管理员ID=1  不能进行修改操作
		if( id == 1){
			model.addAttribute("error","操作提示：该角色为系统管理员，不能修改");
			return "forward:/base/role/list";
		}
		
		Role role = roleService.getRole(id);
		roleCommand.setRoleId(id);
		roleCommand.setName(role.getName());
		roleCommand.setDescription(role.getDescription());
		List<String> checkedRolePermissionSet = permissionService.selectPermissionsByRoleId(id);
		roleCommand.setPermissions(checkedRolePermissionSet.toString().
				substring(1, checkedRolePermissionSet.toString().length()-1));
        
        User currentUser = userService.getCurrentUser();
    	List<Permission> plist = permissionService.getAllPermission();
    	
    	//stripped simple permission string to a set to compare and mixed with current permission
    	HashSet<String> allRolePermissionSet = new HashSet<String>();
    	for (Permission p : plist) {
    		allRolePermissionSet.add(p.getElement());
		}
    	
    	//get all of permissions from current role that related with current user
    	HashSet<String> currentRolePermissionSet = new HashSet<String>();
    	List<Role> listCurrentRole = roleService.selectRolesByUserId(currentUser.getId());
    	List<String> currentList = new ArrayList<String>();
    	for (Role r : listCurrentRole) {
    		currentList = permissionService.selectPermissionsByRoleId(r.getId());
    		currentRolePermissionSet.addAll(currentList);
		}
    	
    	//compare and get mixed permissions
    	allRolePermissionSet.retainAll(currentRolePermissionSet);
    	
    	String jsonStr = "";
    	for (Permission permission : plist) {
    		if(allRolePermissionSet.contains(permission.getElement()) 
    				|| permission.getElement().contains(":-")){
	    		String tmp = "{'pid':"+"'"+permission.getParentid()+"'"+"," +
	    				"'input':"+"'"+permission.getElement()+"'"+"," +
	    				"'text':"+"'"+permission.getName()+"'"+"," +
	    				"'id':"+"'"+permission.getId()+"'"+"," +
	    				"'checked':"+ checkStatus(checkedRolePermissionSet,
	    						permission.getElement()) + "}";
	    		jsonStr += tmp+","; 
    		}
		}
    	
    	model.addAttribute("id", id);
    	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
        return "role/edit";
    }
    
	/**
	 * 修改角色
	 * @param model
	 * @param id
	 * @param roleCommand
	 * @return
	 */
	 @RequestMapping(value = BaseUrl.ROLE_EDIT, method= RequestMethod.POST)
     @RequiresPermissions("role:edit")
     public String edit(Model model, @PathVariable Long id, @ModelAttribute RoleCommand roleCommand) {
		 logger.info("调用[角色修改接口]服务WEB后台接口,入参id：{}，参数为：{}", id, roleCommand);
		/*********************防止POST 注入进行越权修改角色信息  start***************************/
		//系统管理员ID=1  不能进行修改操作
		if( id == 1){
			model.addAttribute("error","操作提示:该角色为系统管理员，不能修改");
			return "forward:/base/role/list";
		} 
		/*********************防止POST 注入进行越权修改角色信息  end***************************/
		
		if(roleCommand.getName() == null || "".equals(roleCommand.getName().trim())){
    		model.addAttribute("error", "角色的角色名不能为空,已恢复至修改之前状态！");
    		return editForm(model, id, roleCommand);
    	}
		
		Role role = roleService.getRole(id);
        roleService.updateRole(role, roleCommand);
        return "redirect:/base/role/list";
     }
	
	 /**
	  * 查看角色
	  * @param model
	  * @param id
	  * @param roleCommand
	  * @return
	  */
     @RequestMapping(value = BaseUrl.ROLE_VIEW, method = RequestMethod.GET)
     @RequiresPermissions("role:view")
     public String show(Model model, @PathVariable Long id, @ModelAttribute RoleCommand roleCommand) {
     	
    	 Role role = roleService.getRole( id );
    	 roleCommand.setRoleId(id);
    	 roleCommand.setName(role.getName());
    	 roleCommand.setDescription(role.getDescription());
    	 List<String> checkedRolePermissionSet = permissionService.selectPermissionsByRoleId(id);
    	 roleCommand.setPermissions(checkedRolePermissionSet.toString().
 				substring(1, checkedRolePermissionSet.toString().length()-1));
         
    	 User currentUser = userService.getCurrentUser();
    	 List<Permission> plist = permissionService.getAllPermission();
     	
     	//stripped simple permission string to a set to compare and mixed with current permission
    	 HashSet<String> allRolePermissionSet = new HashSet<String>();
    	 for (Permission p : plist) {
    		 allRolePermissionSet.add(p.getElement());
    	 }
     	
     	//get all of permissions from current role that related with current user
     	HashSet<String> currentRolePermissionSet = new HashSet<String>();
     	List<Role> listCurrentRole = roleService.selectRolesByUserId(currentUser.getId());
     	List<String> currentList = new ArrayList<String>();
     	for (Role r : listCurrentRole) {
     		currentList = permissionService.selectPermissionsByRoleId(r.getId());
     		currentRolePermissionSet.addAll(currentList);
 		}
     	
     	//compare and get mixed permissions
     	allRolePermissionSet.retainAll(currentRolePermissionSet);
     	
     	String jsonStr = "";
     	for (Permission permission : plist) {
     		if( allRolePermissionSet.contains(permission.getElement()) || permission.getElement().contains(":-")){
 	    		String tmp = "{'pid':"+"'"+permission.getParentid()+"'"+"," +
 	    				"'input':"+"'"+permission.getElement()+"'"+"," +
 	    				"'text':"+"'"+permission.getName()+"'"+"," +
 	    				"'id':"+"'"+permission.getId()+"'"+"," +
 	    				"'checked':"+ checkStatus(checkedRolePermissionSet,permission.getElement()) +
 	    				"}";
 	    		jsonStr += tmp + ","; 
     		}
 		}
     	
     	model.addAttribute("permissionTree", jsonStr.substring(0, jsonStr.length()-1));
     	return "role/view";
     }	

     /**
      * 删除角色
      * @param model
      * @param roleId
      * @return
      */
     @RequestMapping(value = BaseUrl.ROLE_DELETE, method = RequestMethod.GET)
     @RequiresPermissions("role:delete")
     public String delete(Model model, @PathVariable Long id) {
 		//系统管理员ID=1  不能进行删除操作
 		if(id == 1){
 			model.addAttribute("error","操作提示：角色名称为系统管理员的数据不能进行删除！");
 		} else {
 			//this role can`t delete if any user related with it 
 			if (roleService.countUserIdsByRoleId(id) <= 0 ){
 				roleService.deleteRole(id);
 				model.addAttribute("msg", "操作提示：删除成功！");
 			} else {
 				model.addAttribute("error","操作提示：该角色已经关联了用户，不能删除！");
 			}
 		}
 		return "forward:/base/role/list";
     }
     
 	// status 1 means checked, 0 means unchecked
 	private String checkStatus(List<String> list,String pStr){
 		if(list.contains(pStr)){
 			return "'1'";
 		}else{
 			return "'0'";
 		}
 	}
 	
}
