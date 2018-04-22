/*
 * @(#)AddressController.java 2017年12月4日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.core.controller.BaseController;


/**
 *
 * @author zhaomy07
 * @date 2017年12月4日 上午11:48:58
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/api")
public class AddressController extends BaseController{
	
	/**
	 * 新增收货地址
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
	public void addAddress(HttpServletRequest request){
		logger.info("调用[新增收货地址]服务APP接口--Start");
	}
	
}
