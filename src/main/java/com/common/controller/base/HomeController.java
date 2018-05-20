/*
 * @(#)HomeController.java 2018年5月20日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.pojo.base.User;
import com.common.service.base.UserService;

/**
 *
 * @author Administrator
 * @date 2018年5月20日 上午10:13:05
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/base")
public class HomeController extends BaseController{

	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录后进入首页 
	 * 后期可以在这里进行权限判断 进入哪个页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = BaseUrl.USER_HOME, method = RequestMethod.GET)
	public String viewHome(Model model, HttpServletRequest request) {
		return "redirect:/base/user/list";
	}
	
	/**
	 * 用户无权限页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = BaseUrl.USER_UNAUTHORIZED)
    public String unauthorized(Model model) {
    	return "unauthorized";
    }
	
	/**
	 * 后台页面头文件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = BaseUrl.USER_HEAD)
    public String head(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
        	session.setAttribute("currentUser", currentUser);
        }
    	return "user/head";
    }
	
}
