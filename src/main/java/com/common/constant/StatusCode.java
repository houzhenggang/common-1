package com.common.constant;

/**
 *
 * @author zhaomingyang9
 * @date 2018年4月4日 下午4:01:39
 * @version V1.0.0
 * description：
 */
public enum StatusCode {

	STATUS_ON(1, "状态正常（开启）"),

	STATUS_OFF(0, "状态异常（关闭）");

    private int code;
    private String msg;

    private StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
