package com.osmartian.small.app.home.network.interceptor;


import com.osmartian.small.lib.martian.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author   : walid
 * Data     : 2016-08-18  15:59
 * Describe :
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Logger.d("ParamsInterceptor intercept");
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        String authorization = SPUtils.getString(R.string.key_tickte);
//        if (!TextUtils.isEmpty(authorization)) {
//            builder.addHeader("Authorization", authorization);
//            Logger.d("header Authorization = " + authorization);
//        }
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }

}
