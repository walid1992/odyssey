package com.syswin.toon.app.home.network;

import com.google.gson.annotations.Expose;
import com.walid.rxretrofit.interfaces.IHttpResult;

/**
 * Author   : walid
 * Data     : 2016-08-18  15:59
 * Describe : IHttpResult
 */
public class HttpResult<T> implements IHttpResult<T> {

    @Expose
    private int code;
    @Expose
    private String message;
    @Expose
    private T data;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IHttpResult {" + "code=" + code + ", msg='" + message + '\'' + ", data=" + data + '}';
    }

}

