package com.common.core.exception;

public class AppRuntimeException extends BaseRuntimeException {
    private static final long serialVersionUID = -3577333812804703994L;

    public AppRuntimeException(String code, String defaultMessage, Throwable cause, Object... args) {
        super(code, defaultMessage, cause, args);
    }

    public AppRuntimeException(String code, String defaultMessage, Object... args) {
        super(code, defaultMessage, args);
    }

    public AppRuntimeException(String defaultMessage, Throwable cause) {
        super(defaultMessage, cause);
    }

    public AppRuntimeException(String defaultMessage) {
        super(defaultMessage);
    }
}
