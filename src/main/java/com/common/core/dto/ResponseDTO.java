package com.common.core.dto;

import com.common.core.constant.ResponseStatus;
import com.common.core.exception.BaseException;
import com.common.core.model.BaseObject;

public class ResponseDTO<E> extends BaseObject {
    private static final long serialVersionUID = 7094474287531232051L;

    private E data; // 返回数据
    private int status;// 是否成功 (0失败，1成功) ResponseStatus.FAIL; ResponseStatus.SUCCESS
    private String message; // 成功或错误提示信息
    private String errorCode;// 错误编码

    /** 默认状态为“成功” */
    public ResponseDTO() {
        this.status = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMsg();
    }

    public ResponseDTO(ResponseStatus responseStatus) {
        this.setStatus(responseStatus);
    }

    public ResponseDTO(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public ResponseDTO<E> setStatus(int status) {
        return this.setStatus(ResponseStatus.getByCode(status));
    }

    public String getMessage() {
        return message;
    }

    public ResponseDTO<E> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ResponseDTO<E> setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public E getData() {
        return this.data;
    }

    public ResponseDTO<E> setData(E data) {
        this.data = data;
        return this;
    }

    public ResponseDTO<E> setStatus(ResponseStatus responseStatus) {
        if (responseStatus != null) {
            this.status = responseStatus.getCode();
            this.message = responseStatus.getMsg();
        }
        return this;
    }

    public ResponseDTO<E> setException(BaseException exception) {
        if (exception != null) {
            this.status = ResponseStatus.FAIL.getCode();
            this.errorCode = exception.getCode();
            this.message = exception.getMessage();
        }
        return this;
    }

    public ResponseDTO<E> setResult(ResponseStatus responseStatus, String errorCode) {
        if (responseStatus != null) {
            this.status = responseStatus.getCode();
            this.message = responseStatus.getMsg();
        }
        this.errorCode = errorCode;
        return this;
    }

    public ResponseDTO<E> setResult(int status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public ResponseDTO<E> setResult(int status, String message, String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        return this;
    }

    public ResponseDTO<E> setResult(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        return this;
    }

    public ResponseDTO<E> setResponse(ResponseDTO<?> responseDTO) {
        if (responseDTO != null) {
            this.status = responseDTO.getStatus();
            this.message = responseDTO.getMessage();
            this.errorCode = responseDTO.getErrorCode();
        }
        return this;
    }
}
