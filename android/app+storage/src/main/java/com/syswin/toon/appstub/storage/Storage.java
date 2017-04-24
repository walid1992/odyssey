package com.syswin.toon.appstub.storage;

import android.content.Context;

import com.syswin.toon.appstub.storage.bean.UserInfo;
import com.syswin.toon.appstub.storage.utils.SPUtils;

/**
 * @Author : walid
 * @Data : 2017-04-11  14:25
 * @Describe :
 */
public class Storage {

    private Storage() {
    }

    private static class Singleton {
        static Storage instance = new Storage();
    }

    public static Storage getInstance() {
        return Singleton.instance;
    }

    public UserInfo getUserInfo(Context ctx) {
        SPUtils.put(ctx, "userId", "312790");
        SPUtils.put(ctx, "token", "b636f162-ef52-47a1-aeb6-76323f294a79");
        UserInfo userInfo = new UserInfo();
        userInfo.setToken(SPUtils.getString(ctx, "userId"));
        userInfo.setToken(SPUtils.getString(ctx, "token"));
        return userInfo;
    }

    public void setUserInfo(Context ctx, UserInfo userInfo) {
        SPUtils.put(ctx, "userId", userInfo.getUserId());
        SPUtils.put(ctx, "token", userInfo.getToken());
    }

}
