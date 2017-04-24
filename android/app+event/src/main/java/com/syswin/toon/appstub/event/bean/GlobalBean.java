package com.syswin.toon.appstub.event.bean;

/**
 * @Author : walid
 * @Data : 2017-04-07  11:14
 * @Describe :
 */
public class GlobalBean {

    @Key
    private int key;
    private Value value;

    public GlobalBean(@Key int eventKey, Value eventValue) {
        this.key = eventKey;
        this.value = eventValue;
    }

    public int getKey() {
        return key;
    }

    public void setKey(@Key int key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
