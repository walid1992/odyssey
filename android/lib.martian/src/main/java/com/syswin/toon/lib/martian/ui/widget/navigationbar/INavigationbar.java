package com.syswin.toon.lib.martian.ui.widget.navigationbar;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author   : walid
 * Data     : 2016-09-06  18:53
 * Describe :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface INavigationbar {

    @ColorRes
    int backgroundColor() default -1;

    String titleText();

    @ColorRes
    int titleColor() default -1;

    @DrawableRes
    int leftSrc() default -1;

    boolean showBack() default false;

}
