package com.osmartian.small.lib.martian.utils.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Author   : walid
 * Data     : 2016-09-06  17:38
 * Describe : EventBus事件中心
 */

public class MartianEvent {

    private static EventBus getDefault() {
        return EventBus.getDefault();
    }

    public static void post(Object o) {
        getDefault().post(o);
    }

    public static void register(Object o) {
        if (getDefault().isRegistered(o)) {
            return;
        }
        getDefault().register(o);
    }

    public static void unregister(Object o) {
        if (!getDefault().isRegistered(o)) {
            return;
        }
        getDefault().unregister(o);
    }

}
