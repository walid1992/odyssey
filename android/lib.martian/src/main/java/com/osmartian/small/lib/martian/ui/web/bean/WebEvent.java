package com.osmartian.small.lib.martian.ui.web.bean;

import android.support.annotation.IntDef;

import com.osmartian.small.lib.martian.ui.web.WebActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-09-06  22:49
 * Describe : web activity event 事件分发
 */

public class WebEvent {

    @IntDef({WebEvent.TYPE.LEFT, WebEvent.TYPE.TITLE, WebEvent.TYPE.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        int LEFT = 0;
        int TITLE = 1;
        int RIGHT = 2;
    }

    private WebActivity webActivity;
    @TYPE
    private int type;
    private String tag;

    public WebEvent(WebActivity webActivity, int type, String tag) {
        this.webActivity = webActivity;
        this.type = type;
        this.tag = tag;
    }

    public WebActivity getWebActivity() {
        return webActivity;
    }

    public void setWebActivity(WebActivity webActivity) {
        this.webActivity = webActivity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
