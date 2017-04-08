package com.osmartian.small.appstub.event.bean;

/**
 * @Author : walid
 * @Data : 2017-04-07  11:14
 * @Describe : global event data
 */
public class Value {

    private int code;
    private String msg;
    private String data;

    public Value(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
