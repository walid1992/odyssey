package com.syswin.toon.appstub.event.promise;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author : walid
 * @Data : 2017-04-08  15:12
 * @Describe : state 枚举
 */

@IntDef({State.PENDING, State.RESOLVE, State.REJECT})
@Retention(RetentionPolicy.SOURCE)
public @interface State {
    int PENDING = 1;
    int RESOLVE = 2;
    int REJECT = 3;
}
