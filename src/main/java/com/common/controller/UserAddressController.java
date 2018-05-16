/*
 * @(#)AddressController.java 2017年12月4日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.constant.AppUrl;
import com.common.core.controller.BaseController;
import com.common.core.dto.ResponseDTO;
import com.common.dto.UserAddressDTO;
import com.common.exception.BussinessException;
import com.common.pojo.UserAddress;
import com.common.service.UserAddressService;
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
@RequestMapping("/api")
public class UserAddressController extends BaseController{
	
	@Autowired
	private UserAddressService userAddressService;
	
	/**
	 * 查询地址详情
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = AppUrl.USER_ADDRESS_DETAIL, method = RequestMethod.POST)
    @ResponseBody
    ResponseDTO<UserAddress> selectById(@RequestBody UserAddressDTO dto){
		logger.info("调用[查询地址详情]服务APP接口--Start,入参：{}", dto);
		ResponseDTO<UserAddress> response = new ResponseDTO<UserAddress>();
		try {
			response = userAddressService.selectById(dto);
		} catch (BussinessException e) {
            logger.error("[查询地址详情]业务异常：{}", e); 
            response.setException(e);
        } catch (Exception e) {
            logger.error("[查询地址详情]系统异常：{}", e); 
            ResponseUtils.failed(response);
        }
        logger.info("调用[查询地址详情]服务APP接口--end,结果：{}", response);
        return response;
	}
	
	/**
	 * 新增用户地址
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = AppUrl.USER_ADDRESS_ADD, method = RequestMethod.POST)
    @ResponseBody
    ResponseDTO<Integer> add(@RequestBody UserAddressDTO dto){
		logger.info("调用[新增用户地址]服务APP接口--Start,入参：{}", dto);
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		try {
			response = userAddressService.add(dto);
		} catch (BussinessException e) {
            logger.error("[新增用户地址]业务异常：{}", e); 
            response.setException(e);
        } catch (Exception e) {
            logger.error("[新增用户地址]系统异常：{}", e); 
            ResponseUtils.failed(response);
        }
        logger.info("调用[新增用户地址]服务APP接口--end,结果：{}", response);
        return response;
	}

	/**
	 * 修改用户地址
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = AppUrl.USER_ADDRESS_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    ResponseDTO<Integer> update(@RequestBody UserAddressDTO dto){
		logger.info("调用[修改用户地址]服务APP接口--Start,入参：{}", dto);
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		try {
			response = userAddressService.update(dto);
		} catch (BussinessException e) {
            logger.error("[修改用户地址]业务异常：{}", e); 
            response.setException(e);
        } catch (Exception e) {
            logger.error("[修改用户地址]系统异常：{}", e); 
            ResponseUtils.failed(response);
        }
        logger.info("调用[修改用户地址]服务APP接口--end,结果：{}", response);
        return response;
	}
	
	/**
	 * 删除用户地址
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = AppUrl.USER_ADDRESS_DELETE, method = RequestMethod.POST)
    @ResponseBody
    ResponseDTO<Integer> delete(@RequestBody UserAddressDTO dto){
		logger.info("调用[删除用户地址]服务APP接口--Start,入参：{}", dto);
		ResponseDTO<Integer> response = new ResponseDTO<Integer>();
		try {
			response = userAddressService.delete(dto);
		} catch (BussinessException e) {
            logger.error("[删除用户地址]业务异常：{}", e); 
            response.setException(e);
        } catch (Exception e) {
            logger.error("[删除用户地址]系统异常：{}", e); 
            ResponseUtils.failed(response);
        }
        logger.info("调用[新增用户地址]服务APP接口--end,结果：{}", response);
        return response;
	}
	
}
