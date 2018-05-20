/*
 * @(#)UserServiceImpl.java 2018年5月19日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.base.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.base.UserRoleDAO;
import com.common.command.UserCommand;
import com.common.dao.base.UserDAO;
import com.common.pojo.base.User;
import com.common.pojo.base.UserRole;
import com.common.service.base.UserService;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午1:43:40
 * @version V1.0.0 description：
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public User getCurrentUser() {
		Long currentUserId = (Long) SecurityUtils.getSubject().getPrincipal();
		if (currentUserId != null) {
			return getUser(currentUserId);
		} else {
			return null;
		}
	}

	@Override
	public User getUser(Long userId) {
		return userDAO.selectByPrimaryKey(userId);
	}

	@Override
	public User selectByUsername(String username) {
		return userDAO.selectByUsername(username);
	}
	
	@Override
	public Long createUser(UserCommand command) {
		User user = new User();
		user.setUsername(command.getUsername());
		user.setEmail(command.getEmail());
		user.setPassword(new Sha256Hash(command.getPassword()).toHex());
		int result = userDAO.insertSelective(user);
		if (result == 1) {
			String[] rids = command.getRoleIds().split(",");
			UserRole userRole = null;
			for (String roleId : rids) {
				userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(Long.parseLong(roleId));
				userRoleDAO.insertSelective(userRole);
			}
			return 1L;
		} else {
			return 0L;
		}
	}

	@Override
	public Long deleteUser(Long userId) {
		int result = userDAO.deleteByPrimaryKey(userId);
		if (result == 1) {
			//清理权限
			userRoleDAO.deleteRoleIdsByUserId(userId);
			return 1L;
		} else {
			return 0L;
		}
	}

	@Override
	public Long updateUser(User user, UserCommand command) {
		user.setUsername(command.getUsername());
		user.setEmail(command.getEmail());
		if (command.getPassword() != null && !"".equals(command.getPassword())) {
			user.setPassword(new Sha256Hash(command.getPassword()).toHex());
		}
		int result = userDAO.updateByPrimaryKeySelective(user);
		if (result == 1) {
			//清理权限
			userRoleDAO.deleteRoleIdsByUserId(user.getId());
			//重新赋予权限
			String[] rids = command.getRoleIds().split(",");
			UserRole userRole = null;
			for (String roleId : rids) {
				userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(Long.parseLong(roleId));
				userRoleDAO.insertSelective(userRole);
			}
			return 1L;
		} else {
			return 0L;
		}
	}

	@Override
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}

}
