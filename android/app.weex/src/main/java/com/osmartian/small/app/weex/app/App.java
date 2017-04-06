package com.osmartian.small.app.weex.app;


import android.app.Application;
import android.util.Log;

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
        Log.e("AppTAG", "weex App onCreate");
        WeexLibInstance.getInstance().init(this);
    }
}