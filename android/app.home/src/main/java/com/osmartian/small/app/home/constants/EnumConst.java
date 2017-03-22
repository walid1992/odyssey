package com.osmartian.small.app.home.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-09-08  23:38
 * Describe : 枚举常量
 */
public class EnumConst {

    @IntDef({OrderType.ESTATE, OrderType.BUILDING, OrderType.SCHOOL, OrderType.SCENIC, OrderType.HOUSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrderType {
        int ESTATE = 0;
        int BUILDING = 1;
        int SCHOOL = 2;
        int SCENIC = 3;
        int HOUSE = 4;
    }

}
