package com.common.util;

import com.common.core.constant.ResponseStatus;
import com.common.core.dto.ResponseDTO;

/**
 * 
 * @author zhaomy07
 * @date 2017年10月9日 下午2:49:28
 * @version V1.0.0
 * description：
 */
public class ResponseUtils {

    /**
     * 通用失败
     * @param response
     */
    public static void failed(ResponseDTO<?> response, String errorCode){
        response.setStatus(ResponseStatus.FAIL.getCode());
        response.setMessage(ResponseStatus.FAIL.getMsg());
        response.setErrorCode(errorCode);
    }
    
    public static void failedWithMsg(ResponseDTO<?> response, String erroryMsg){
        response.setStatus(ResponseStatus.FAIL.getCode());
        response.setMessage(erroryMsg);
    }
    
    public static void failed(ResponseDTO<?> response){
        failed(response, null);
    }

    /**
     * 通用成功
     */
    public static void success(ResponseDTO<?> response){
        response.setStatus(ResponseStatus.SUCCESS.getCode());
        response.setMessage(ResponseStatus.SUCCESS.getMsg());
    }
}
