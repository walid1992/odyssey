package com.syswin.toon.appstub.event;

import android.util.SparseArray;

import com.syswin.toon.appstub.event.bean.GlobalBean;
import com.syswin.toon.appstub.event.bean.Key;
import com.syswin.toon.appstub.event.promise.Promise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author   : walid
 * Data     : 2017-04-08  17:38
 * Describe : GlobalEvent事件中心
 */

public class GlobalEvent {

    // key:Object value：<key:Integer value:Promise>
    private Map<Object, SparseArray<Promise<GlobalBean, String>>> listenerMap = new HashMap<>();

    private GlobalEvent() {
    }

    private static class SingletonHolder {
        static GlobalEvent instance = new GlobalEvent();
    }

    public static void initWeexCall() {

    }

    public static Promise<GlobalBean, String> register(Object o, @Key int key) {
        Map<Object, SparseArray<Promise<GlobalBean, String>>> listenerMap = SingletonHolder.instance.listenerMap;
        SparseArray<Promise<GlobalBean, String>> sparseArray = listenerMap.get(o) != null ? listenerMap.get(o) : new SparseArray<>();
        Promise<GlobalBean, String> promise = new Promise<>((resolve, reject) -> {
        });
        sparseArray.put(key, promise);
        listenerMap.put(o, sparseArray);
        return promise;
    }

    public static void unRegister(Object o) {
        SingletonHolder.instance.listenerMap.remove(o);
    }

    public static void post(GlobalBean globalBean) {
        GlobalEvent globalEvent = SingletonHolder.instance;
        Set<Map.Entry<Object, SparseArray<Promise<GlobalBean, String>>>> entrySet = globalEvent.listenerMap.entrySet();
        for (Map.Entry<Object, SparseArray<Promise<GlobalBean, String>>> entry : entrySet) {
            Promise<GlobalBean, String> promise = entry.getValue().get(globalBean.getKey());
            if (promise != null) {
                promise.resolve.run(globalBean);
            }
        }
    }

}
