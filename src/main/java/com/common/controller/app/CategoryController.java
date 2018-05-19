/*
 * @(#)AddressController.java 2017年12月4日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.constant.AppUrl;
import com.common.core.controller.BaseController;
import com.common.core.dto.ResponseDTO;
import com.common.dto.CategoryDTO;
import com.common.exception.BussinessException;
import com.common.service.app.CategoryService;
import com.common.util.ResponseUtils;


/**
 *
 * @author zhaomy07
 * @date 2017年12月4日 上午11:48:58
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/app")
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 查询所有类型
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = AppUrl.CATEGORY_SELECT_ALL, method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<CategoryDTO>> selectAll(){
		ResponseDTO<List<CategoryDTO>> response = new ResponseDTO<List<CategoryDTO>>();
		try {
			response = categoryService.selectAll();
		} catch (BussinessException e) {
            logger.error("[查询所有类型]业务异常：{}", e); 
            response.setException(e);
        } catch (Exception e) {
            logger.error("[查询所有类型]系统异常：{}", e); 
            ResponseUtils.failed(response);
        }
        logger.info("调用[查询所有类型]服务APP接口--end,结果：{}", response);
        return response;
	}
	
}
