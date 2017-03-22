package com.osmartian.small.app.home.app;

import android.graphics.Color;

import com.google.gson.GsonBuilder;
import com.osmartian.small.app.home.constants.AppConstants;
import com.osmartian.small.app.home.network.SeaCodeVerify;
import com.osmartian.small.app.home.network.interceptor.ParamsInterceptor;
import com.osmartian.small.lib.martian.app.MartianApp;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.NavigationbarUtils;
import com.walid.autolayout.config.AutoLayoutConifg;
import com.walid.rxretrofit.HttpManager;
import com.walid.rxretrofit.bean.RetrofitParams;

import java.util.ArrayList;

import okhttp3.Interceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author   : walid
 * Data     : 2016-10-20  17:17
 * Describe :
 */
public class App extends MartianApp {

    @Override
    protected void init() {
        AutoLayoutConifg.getInstance().initConfig(this, 640, 1136);
        initRxJavaRetrofit();
        NavigationbarUtils.sNavigationBarBackgroundColor = Color.parseColor("#1296db");
        NavigationbarUtils.sNavigationBarBackStyle = NavigationbarUtils.BackStyle.WHITH;
        NavigationbarUtils.sNavigationBarTitleColor = Color.WHITE;
    }

    private void initRxJavaRetrofit() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new ParamsInterceptor());
        RetrofitParams params = new RetrofitParams();
        // data 转换器
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        params.setConverterFactory(GsonConverterFactory.create(builder.create()));
        params.setConnectTimeoutSeconds(10);
        params.setReadTimeoutSeconds(10);
        params.setWriteTimeoutSeconds(10);
        params.setInterceptors(interceptors);
        params.setDebug(true);
        HttpManager.getInstance().create(AppConstants.SEA_URL, new SeaCodeVerify(), params);
    }

}
