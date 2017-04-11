package com.osmartian.small.app.weex.app;


import android.app.Application;
import android.util.Log;

import com.osmartian.small.app.weex.module.WxUserInfoModule;
import com.osmartian.small.lib.weex.app.WeexLibInstance;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * @Author : Walid
 * @Data : 2017-2-20  15:01
 * @Describe :
 */
public class App extends Application {

    private static App sContext;

    public static App getInstance() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Log.e("AppTAG", "weex App onCreate");
        try {
            WeexLibInstance.getInstance().init(this);
            WXSDKEngine.registerModule("user", WxUserInfoModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}