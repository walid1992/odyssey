package com.osmartian.small.app.main2.app;


import android.app.Application;

import com.osmartian.small.lib.weex.app.WeexLibInstance;

/**
 * @Author : Walid
 * @Data : 2017-2-20  15:01
 * @Describe :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WeexLibInstance.getInstance().init(this);
    }
}