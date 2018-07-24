/*
 * @(#)LoginController.java 2018年5月19日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.command.LoginCommand;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午10:13:53
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/base")
public class LoginController extends BaseController{

	@RequestMapping(value = BaseUrl.USER_LOGIN, method = RequestMethod.GET)
	public String loginForm() {
		return "user/login";
	}
	
	@RequestMapping(value = BaseUrl.USER_LOGIN, method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request , @ModelAttribute LoginCommand command) {
		logger.info("调用[用户登录接口]服务WEB后台接口,入参：{}", command);
		HttpSession session = request.getSession();
		if (command.getUsername() == null || "".equals(command.getUsername().trim())) {
			model.addAttribute("error", "用户名不能为空！");
			return "user/login";
		}
		
		if (command.getPassword() == null || "".equals(command.getPassword().trim())) {
			model.addAttribute("error", "密码不能为空！");
			return "user/login";
		}
		
		if (command.getCaptcha() == null || "".equals(command.getCaptcha().trim())) {
			model.addAttribute("error", "验证码不能为空！");
			return "user/login";
		}
		
    	if (!command.getCaptcha().equalsIgnoreCase(session.getAttribute("picLogincode").toString())) {
    		model.addAttribute("error", "验证码不一致！");
			return "user/login";
    	}
		
		// 使用权限工具进行用户登录
		UsernamePasswordToken token = new UsernamePasswordToken(command.getUsername(), command.getPassword(), false);
		try {
			SecurityUtils.getSubject().login(token);
			logger.info("sessionID=[{}]", SecurityUtils.getSubject().getSession().getId());
		} catch (AuthenticationException e) {
			model.addAttribute("error", "用户名或密码错误，请重新输入！");
			return loginForm();
		}
		return "redirect:/base/user/home";
	}

	@RequestMapping(value = BaseUrl.USER_LOGOUT, method = RequestMethod.GET)
	public String logout() {
		// 使用权限管理工具进行用户的退出
		SecurityUtils.getSubject().logout();
		return "redirect:/base/user/login";
	}
	
	
}
