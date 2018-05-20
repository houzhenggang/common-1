/*
 * @(#)UserService.java 2018年5月19日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.base;

import java.util.List;

import com.common.command.UserCommand;
import com.common.pojo.base.User;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午1:33:50
 * @version V1.0.0
 * description：
 * 
 */
public interface UserService {

	User getCurrentUser();
	
	User getUser(Long userId);
	
	User selectByUsername(String username);
	
	Long createUser(UserCommand command);

	Long deleteUser(Long userId);

	Long updateUser(User user, UserCommand command);
    
    List<User> getAllUser();
	
}
