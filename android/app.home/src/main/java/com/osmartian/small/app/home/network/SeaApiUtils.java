package com.osmartian.small.app.home.network;

import com.osmartian.small.app.home.app.App;
import com.walid.rxretrofit.HttpManager;
import com.walid.rxretrofit.HttpSubscriber;
import com.walid.rxretrofit.interfaces.IHttpCallback;

import rx.Observable;

/**
 * Author   : walid
 * Data     : 2016-08-18  16:09
 * Describe :
 */
public class SeaApiUtils {

    public static <T> HttpSubscriber<T> toSubscribe(Observable<HttpResult<T>> observable, final IHttpCallback<T> listener) {
        return toSubscribe(observable, listener, true);
    }

    public static <T> HttpSubscriber<T> toSubscribe(Observable<HttpResult<T>> observable, final IHttpCallback<T> listener, boolean showToast) {
        return HttpManager.getInstance().toSubscribe(observable, App.instance(), listener, showToast);
    }

}
