package com.common.core.constant;

public enum ResponseStatus {
    FAIL(0, "失败"),

    SUCCESS(1, "成功");

    private int code;
    private String msg;

    private ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static ResponseStatus getByCode(int code) {
        for (ResponseStatus value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
