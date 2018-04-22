package com.common.exception;

import com.common.constant.ErrorCode;
import com.common.core.exception.AppRuntimeException;

/**
 *
 * @author zhaomingyang9
 * @date 2018年4月4日 下午4:20:31
 * @version V1.0.0
 * description：
 */
public class BussinessException extends AppRuntimeException {
    private static final long serialVersionUID = 322084776838773529L;

    public BussinessException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMsg());
    }

    public BussinessException(String msg) {
        super(msg);
    }

    public BussinessException(String code, String msg) {
        super(code, msg);
    }

    public BussinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BussinessException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
