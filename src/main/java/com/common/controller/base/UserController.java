/*
 * @(#)UserController.java 2018年5月20日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
import com.common.command.UserCommand;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.dto.base.UserDTO;
import com.common.pojo.base.Role;
import com.common.pojo.base.User;
import com.common.service.base.PermissionService;
import com.common.service.base.RoleService;
import com.common.service.base.UserService;

/**
 *
 * @author Administrator
 * @date 2018年5月20日 上午11:11:25
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/base")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 查看用户列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = BaseUrl.USER_LIST)
	@RequiresPermissions("user:manage")
	public String list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<UserDTO> listUser = new ArrayList<UserDTO>();
		List<User> list = userService.getAllUser();
		if (list != null && !list.isEmpty()) {
			UserDTO dto = null;
			for (User user : list) {
				dto = new UserDTO();
				dto.setId(user.getId());
				dto.setPassword(user.getPassword());
				dto.setEmail(user.getEmail());
				dto.setUsername(user.getUsername());
				dto.setRoles(roleService.selectRolesByUserId(user.getId()));
				listUser.add(dto);
			}
		}
    	session.setAttribute("listUser", listUser);
    	return "user/list";
	}
	
	/**
	 * 查看用户
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = BaseUrl.USER_VIEW, method = RequestMethod.GET)
	@RequiresPermissions("user:view")
	public String view(Model model, @PathVariable Long id) {
		User user = userService.getUser(id);
		List<Role> listRole = roleService.selectRolesByUserId(id);
		model.addAttribute("user", user);
		model.addAttribute("listRole", listRole);
		return "user/view";
	}
	
	/**
     * 新增用户
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = BaseUrl.USER_ADD, method = RequestMethod.GET)
    @RequiresPermissions("user:add")
    public String addForm(Model model, @ModelAttribute UserCommand userCommand) {
    	List<Role> haveRoleList = new ArrayList<Role>();
    	
    	List<Role> listAllRole = roleService.getAllRole();
    	User currentUser = userService.getCurrentUser();
    	List<Role> listCurrentRole = roleService.selectRolesByUserId(currentUser.getId());
    	List<String> currentList = new ArrayList<String>();
    	List<String> allList = new ArrayList<String>();
    	for (Role currentRole : listCurrentRole) {
    		for (Role allRole : listAllRole) {
    			currentList = permissionService.selectPermissionsByRoleId(currentRole.getId());
    			allList = permissionService.selectPermissionsByRoleId(allRole.getId());
				if(currentList.containsAll(allList) && !currentRole.getId().equals(allRole.getId())){
					haveRoleList.add(allRole);
				}
			}
    		haveRoleList.add(currentRole);
		}
    	
    	// list转set 去重复
    	Set<Role> set = new LinkedHashSet<Role>(haveRoleList);  
    	haveRoleList = new ArrayList<Role>(set);
    	
    	List<RoleCommand> roleCommandList = new ArrayList<RoleCommand>();
    	for (Role role : haveRoleList) {
    		RoleCommand rc = new RoleCommand();
    		rc.setRoleId(role.getId());
    		rc.setName(role.getName());
    		rc.setDescription(role.getDescription());
    		roleCommandList.add(rc);
		}
    	
    	model.addAttribute("roleCommandList", roleCommandList);
        return "user/add";
    }
	
    /**
     * 新增用户
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = BaseUrl.USER_ADD, method = RequestMethod.POST)
    @RequiresPermissions("user:add")
    public String add(Model model, @ModelAttribute UserCommand userCommand) {
    	logger.info("调用[用户新增接口]服务WEB后台接口,入参：{}", userCommand);
    	if(userCommand.getUsername() == null || "".equals(userCommand.getUsername().trim())){
    		model.addAttribute("error", "新增用户的用户名不能为空");
    		return addForm(model, userCommand);
    	}
    	
    	User user = userService.selectByUsername(userCommand.getUsername());
    	if(user != null){
    		model.addAttribute("error", "该用户名已经存在，请重新输入您的用户名");
    		return addForm(model, userCommand);
    	}
    	
    	if(userCommand.getPassword() == null || "".equals(userCommand.getPassword().trim())){
    		model.addAttribute("error", "新增用户的密码不能为空");
    		return addForm(model, userCommand);
    	}
    	
    	if(userCommand.getEmail() == null || "".equals(userCommand.getEmail().trim())){
    		model.addAttribute("error", "新增用户的邮箱不能为空");
    		return addForm(model, userCommand);
    	}
    	
        userService.createUser(userCommand);
        return "redirect:/base/user/list";
    }

    /**
     * 修改用户信息
     * @param model
     * @param id
     * @param command
     * @return
     */
	@RequestMapping(value = BaseUrl.USER_EDIT, method = RequestMethod.GET)
	@RequiresPermissions("user:edit")
	public String editForm(Model model, @PathVariable Long id, @ModelAttribute UserCommand userCommand) {
		User user = userService.getUser(id);
		userCommand.setUserId(id);
		userCommand.setUsername(user.getUsername());
		userCommand.setEmail(user.getEmail());

		User currentUser = userService.getCurrentUser();
		// 如果当前用户权限包括了需要修改的用户权限 则可以进行修改 否则权限不够 不能修改
		if (!currentRoleContainsUserRole(currentUser, user)) {
			model.addAttribute("error", "操作提示：您的权限不够，无法修改此用户的信息");
			return "forward:/base/user/list";
		}
		List<Role> haveRoleList = new ArrayList<Role>();
		List<Role> listAllRole = roleService.getAllRole();
		List<Role> listCurrentRole = roleService.selectRolesByUserId(currentUser.getId());
		List<String> currentList = new ArrayList<String>();
    	List<String> allList = new ArrayList<String>();
    	for (Role currentRole : listCurrentRole) {
    		for (Role allRole : listAllRole) {
    			currentList = permissionService.selectPermissionsByRoleId(currentRole.getId());
    			allList = permissionService.selectPermissionsByRoleId(allRole.getId());
				if(currentList.containsAll(allList) && !currentRole.getId().equals(allRole.getId())){
					haveRoleList.add(allRole);
				}
			}
    		haveRoleList.add(currentRole);
		}

    	// list转set 去重复
    	Set<Role> set = new LinkedHashSet<Role>(haveRoleList);  
    	haveRoleList = new ArrayList<Role>(set);

		List<RoleCommand> originalCommandList = new ArrayList<RoleCommand>();
		List<Role> userListRole = roleService.selectRolesByUserId(user.getId());
		for (Role role : userListRole) {
			RoleCommand rc = new RoleCommand();
			rc.setRoleId(role.getId());
			rc.setName(role.getName());
			rc.setDescription(role.getDescription());
			originalCommandList.add(rc);
		}

		List<RoleCommand> roleCommandList = new ArrayList<RoleCommand>();
		for (Role role : haveRoleList) {
			RoleCommand rc = new RoleCommand();
			rc.setRoleId(role.getId());
			rc.setName(role.getName());
			rc.setDescription(role.getDescription());
			roleCommandList.add(rc);
		}
		
		for (RoleCommand rc : originalCommandList) {
			roleCommandList.remove(rc);
		}
		model.addAttribute("id", id);
		model.addAttribute("originalCommandList", originalCommandList);
		model.addAttribute("roleCommandList", roleCommandList);
		return "user/edit";
	}

	@RequestMapping(value = BaseUrl.USER_EDIT, method = RequestMethod.POST)
	@RequiresPermissions("user:edit")
	public String edit(Model model, @PathVariable Long id, @ModelAttribute UserCommand userCommand) {
		logger.info("调用[修改新增接口]服务WEB后台接口,入参：{}", userCommand);
		if(userCommand.getUsername() == null || "".equals(userCommand.getUsername().trim())){
    		model.addAttribute("error", "用户名不能为空,已恢复至修改之前状态！");
    		return editForm(model, id , userCommand);
    	}
    	
    	if(userCommand.getEmail() == null || "".equals(userCommand.getEmail().trim())){
    		model.addAttribute("error", "用户的邮箱不能为空,已恢复至修改之前状态！");
    		return editForm(model, id , userCommand);
    	}
    	
		User user = userService.getUser(id);
		User currentUser = userService.getCurrentUser();
		/*********************
		 * 防止POST 注入进行越权修改用户信息 start
		 ***************************/
		// 如果当前用户权限包括了需要修改的用户权限 则可以进行修改 否则权限不够 不能修改
		if (!currentRoleContainsUserRole(currentUser, user)) {
			model.addAttribute("error", "操作提示：您的权限不够，无法修改此用户的信息");
			return "forward:/base/user/list";
		}
		/*********************
		 * 防止POST 注入进行越权修改用户信息 end
		 ***************************/
		
		userService.updateUser(user, userCommand);
		return "redirect:/base/user/list";
	}

	@RequestMapping(value = BaseUrl.USER_DELETE, method = RequestMethod.GET)
	@RequiresPermissions("user:delete")
	public String delete(Model model, @PathVariable Long id) {
		if (id == 1) {
			model.addAttribute("error", "操作提示：系统管理员用户不能进行删除操作");
			return "forward:/base/user/list";
		}
		String tmpUser = "";
		// 如果当前用户权限包括了需要修改的用户权限 则可以进行删除 否则权限不够 不能删除
		if (!currentRoleContainsUserRole(userService.getCurrentUser(), userService.getUser(id))) {
			tmpUser = userService.getUser(id).getUsername();
		} else {
			userService.deleteUser(id);
		}
		if ("".equals(tmpUser)) {
			model.addAttribute("msg", "操作提示：删除成功！");
		} else {
			model.addAttribute("error", "操作提示：由于您的权限不够，您将无法对用户名为: " + tmpUser + "的用户进行删除操作");
		}
		return "forward:/base/user/list";
	}
	
	/**
	 * 判断当前登陆用户是否有权限能进行操作此用户
	 * 
	 * @param currentUser
	 * @param user
	 * @return
	 */
	private boolean currentRoleContainsUserRole(User currentUser, User user) {
		List<Role> currentUserListRole = roleService.selectRolesByUserId(currentUser.getId());
		List<Role> userListRole = roleService.selectRolesByUserId(user.getId());
		
		List<String> currentList = new ArrayList<String>();
    	List<String> userList = new ArrayList<String>();
    	
		HashSet<String> userRolePermissionSet = new HashSet<String>();
		for (Role ur : userListRole) {
			userList = permissionService.selectPermissionsByRoleId(ur.getId());
			userRolePermissionSet.addAll(userList);
		}
		HashSet<String> currentRolePermissionSet = new HashSet<String>();
		for (Role cr : currentUserListRole) {
			currentList = permissionService.selectPermissionsByRoleId(cr.getId());
			currentRolePermissionSet.addAll(currentList);
		}
		logger.info("查看[当前用户权限集合]返回：{}", userRolePermissionSet);
		logger.info("查看[我的权限集合]返回：{}", currentRolePermissionSet);
		return currentRolePermissionSet.containsAll(userRolePermissionSet);
	}
	
}
