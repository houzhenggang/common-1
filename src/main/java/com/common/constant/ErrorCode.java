package com.common.constant;

/**
 *
 * @author zhaomingyang9
 * @date 2018年4月4日 下午4:01:39
 * @version V1.0.0
 * description：
 */
public enum ErrorCode {

    SYSTEM_ERROR("00", "系统错误"),

    PARAMETER_ERROR("01", "参数错误"),

    PARAMETER_NULL("02", "参数为空");

    private String code;
    private String msg;

    private ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static ErrorCode getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}
