package com.walid.martian.utils.rxjava;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Author   : walid
 * Data     : 2016-09-04  14:48
 * Describe :
 */

public class RxBindingUtils {

    public static void clicks(final Action1<? super Void> onNext, View... views) {
        for (View v : views) {
            RxView.clicks(v).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(onNext);
        }
    }

    public static void longClicks(final Action1<? super Void> onNext, View... views) {
        for (View v : views) {
            RxView.longClicks(v).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(onNext);
        }
    }

}
