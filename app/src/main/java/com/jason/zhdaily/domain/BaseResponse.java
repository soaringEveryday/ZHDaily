package com.jason.zhdaily.domain;

public class BaseResponse<T> {
    protected int status;
    protected String message;
    protected String errorCodes;
    protected T data ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String errorCodes) {
        this.errorCodes = errorCodes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}