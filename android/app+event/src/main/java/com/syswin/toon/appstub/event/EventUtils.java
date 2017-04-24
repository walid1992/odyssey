package com.syswin.toon.appstub.event;

import com.syswin.toon.appstub.event.annotation.RegisterEventBus;
import com.syswin.toon.appstub.event.bean.GlobalBean;

import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Annotation;

/**
 * Author   : walid
 * Data     : 2016-09-06  17:38
 * Describe : EventBus事件中心
 */

public class EventUtils {

    static EventBus getDefault() {
        return EventBus.getDefault();
    }

    public static void post(GlobalBean globalBean) {
        getDefault().post(globalBean);
    }

    public static <T> void init(Class<T> classObj) {
        Annotation annotation = classObj.getAnnotation(RegisterEventBus.class);
        if (annotation != null && ((RegisterEventBus) annotation).isRegister()) {
            EventUtils.register(classObj);
        }
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
