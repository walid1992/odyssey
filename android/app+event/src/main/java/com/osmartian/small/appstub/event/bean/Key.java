package com.osmartian.small.appstub.event.bean;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author : walid
 * @Data : 2017-04-07  11:25
 * @Describe : global event enum key
 */

@IntDef({Key.LOGIN_SUCCESS})
@Retention(RetentionPolicy.SOURCE)
public @interface Key {
    int LOGIN_SUCCESS = 0;
}
