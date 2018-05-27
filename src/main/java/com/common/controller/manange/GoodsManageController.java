/*
 * @(#)GoodsManageController.java 2018年5月26日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.manange;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.command.NoticeCommand;
import com.common.constant.ManageUrl;
import com.common.core.controller.BaseController;

/**
 *
 * @author Administrator
 * @date 2018年5月26日 下午5:13:07
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/manage")
public class GoodsManageController extends BaseController{

    /**
     * 新增商品页面
     * @param model
     * @param command
     * @return
     */
	@RequestMapping(value = ManageUrl.GOODS_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute NoticeCommand noticeCommand){
		return "goods/add";
	}
	
}
