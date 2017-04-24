package com.syswin.toon.appstub.storage.bean;

/**
 * @Author : walid
 * @Data : 2017-04-11  15:17
 * @Describe :
 */
public class UserInfo {
    private String userId;
    private String token;

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
