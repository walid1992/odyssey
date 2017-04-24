package com.syswin.toon.app.weex.module;

import android.support.annotation.Nullable;

import com.syswin.toon.app.weex.app.App;
import com.syswin.toon.appstub.storage.Storage;
import com.syswin.toon.appstub.storage.bean.UserInfo;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : walid
 * @Data : 2017-04-01  09:26
 * @Describe : 用户信息modules
 */
public class WxUserInfoModule extends WXModule {

    // 同步操作 uiThread = false
    // 异步操作 uiThread = true
    @JSMethod(uiThread = false)
    public void getUser(@Nullable final JSCallback callback) {
        Map<String, Object> params = new HashMap<>();
        UserInfo userInfo = Storage.getInstance().getUserInfo(App.getInstance());
        params.put("userId", userInfo.getUserId());
        params.put("token", userInfo.getToken());
        if (callback != null) {
            callback.invoke(params);
        }
    }

}