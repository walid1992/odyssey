package com.osmartian.small.lib.martian.ui.widget.navigationbar;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.osmartian.small.lib.martian.R;
import com.osmartian.small.lib.martian.utils.ResUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-09-08  21:44
 * Describe : Titlebar 辅助
 */

public class NavigationbarUtils {

    @IntDef({BackStyle.BLACK, BackStyle.WHITH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BackStyle {
        int BLACK = 0;
        int WHITH = 1;
    }

    @ColorInt
    public static int sNavigationBarBackgroundColor = Color.BLUE;
    public static int sNavigationBarBackStyle = BackStyle.BLACK;
    @ColorInt
    public static int sNavigationBarTitleColor = Color.WHITE;

    public interface NavigationbarEvent {
        void navigationOnClick();
    }

    public NavigationbarUtils() {
    }

    public static void setting(NavigationBar titleBar, INavigationbar iNavigationbar, NavigationbarEvent navigationbarEvent) {
        if (titleBar == null || iNavigationbar == null) {
            return;
        }

        int backGroundColor = iNavigationbar.backgroundColor();
        if (backGroundColor != -1) {
            titleBar.setBackgroundColor(ResUtils.getColor(backGroundColor));
        } else {
            titleBar.setBackgroundColor(sNavigationBarBackgroundColor);
        }

        int resID = iNavigationbar.leftSrc();
        boolean showBack = iNavigationbar.showBack();

        if (showBack) {
            if (sNavigationBarBackStyle == BackStyle.BLACK) {
                titleBar.setLeftImageResource(R.mipmap.ic_black_back);
            } else {
                titleBar.setLeftImageResource(R.mipmap.ic_white_back);
            }
            titleBar.setLeftClickListener(v -> {
                navigationbarEvent.navigationOnClick();
            });
        } else if (resID != -1) {
            titleBar.setLeftImageResource(resID);
            titleBar.setLeftClickListener(v -> {
                navigationbarEvent.navigationOnClick();
            });
        }

        String title = iNavigationbar.titleText();
        if (!TextUtils.isEmpty(title)) {
            int titleColor = iNavigationbar.titleColor();
            if (titleColor != -1) {
                titleBar.setTitleColor(ResUtils.getColor(titleColor));
            } else {
                titleBar.setTitleColor(sNavigationBarTitleColor);
            }
            titleBar.setTitle(title);
        }
    }

}
