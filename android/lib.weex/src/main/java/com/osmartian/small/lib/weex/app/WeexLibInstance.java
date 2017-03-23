package com.osmartian.small.lib.weex.app;


import android.app.Application;

import com.osmartian.small.lib.weex.weex.ImageAdapter;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * @Author : Walid
 * @Data : 2017-2-20  15:01
 * @Describe : Weex 单利初始化
 */
public class WeexLibInstance {

    private static class LazyHolder {
        private static final WeexLibInstance INSTANCE = new WeexLibInstance();
    }

    private WeexLibInstance() {
    }

    public static WeexLibInstance getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(Application app) {
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(app, config);
    }
}

