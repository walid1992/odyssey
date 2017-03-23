package com.osmartian.small.lib.martian.ui.web.bean;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Author   : walid
 * Data     : 2016-10-23  13:58
 * Describe :
 */

public class WebConfigVo implements Serializable {

    private String title;
    private String rightText;
    private boolean rightTextBold;
    @ColorInt
    private int rightTextColor;
    @DrawableRes
    private int rightSrc;
    private String tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public boolean getRightTextBold() {
        return rightTextBold;
    }

    public void setRightTextBold(boolean rightTextBold) {
        this.rightTextBold = rightTextBold;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public int getRightSrc() {
        return rightSrc;
    }

    public void setRightSrc(int rightSrc) {
        this.rightSrc = rightSrc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
