package com.walid.martian.utils;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import com.walid.martian.app.MartianApp;

public class ResUtils {

    public static String getString(@StringRes int res) {
        return MartianApp.instance().getResources().getString(res);
    }

    public static int getColor(@ColorRes int res) {
        return MartianApp.instance().getResources().getColor(res);
    }

}
