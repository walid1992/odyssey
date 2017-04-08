package com.osmartian.small.appstub.event;

import android.util.SparseArray;

import com.osmartian.small.appstub.event.bean.GlobalBean;
import com.osmartian.small.appstub.event.bean.Key;
import com.osmartian.small.appstub.event.promise.Promise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author   : walid
 * Data     : 2017-04-08  17:38
 * Describe : GlobalEvent事件中心
 */

public class GlobalEvent {

    private Map<Object, SparseArray<List<Promise<GlobalBean, String>>>> listenerMap = new HashMap<>();

    private GlobalEvent() {
//        EventUtils.register(this);
    }

    private static class SingletonHolder {
        static GlobalEvent instance = new GlobalEvent();
    }

    public static Promise<GlobalBean, String> register(Object o, @Key int key) {
        Map<Object, SparseArray<List<Promise<GlobalBean, String>>>> listenerMap = SingletonHolder.instance.listenerMap;
        SparseArray<List<Promise<GlobalBean, String>>> sparseArray = listenerMap.get(o) != null ? listenerMap.get(o) : new SparseArray<>();
        List<Promise<GlobalBean, String>> promises = sparseArray.get(key) != null ? sparseArray.get(key) : new ArrayList<>();
        Promise<GlobalBean, String> promise = new Promise<>((resolve, reject) -> {
        });
        promises.add(promise);
        sparseArray.put(key, promises);
        listenerMap.put(o, sparseArray);
        return promise;
    }

    public static void unRegister(Object o) {
        SingletonHolder.instance.listenerMap.remove(o);
    }

    public static void post(GlobalBean globalBean) {
        GlobalEvent globalEvent = SingletonHolder.instance;
        Set<Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>>> entrySet = globalEvent.listenerMap.entrySet();
        for (Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>> entry : entrySet) {
            List<Promise<GlobalBean, String>> promiseList = entry.getValue().get(globalBean.getKey());
            if (promiseList != null) {
                for (Promise<GlobalBean, String> item : promiseList) {
                    item.resolve.run(globalBean);
                }
            }
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void globalEvent(GlobalBean globalBean) {
//        Set<Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>>> entrySet = listenerMap.entrySet();
//        for (Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>> entry : entrySet) {
//            List<Promise<GlobalBean, String>> promiseList = entry.getValue().get(globalBean.getKey());
//            if (promiseList != null) {
//                for (Promise<GlobalBean, String> item : promiseList) {
//                    item.resolve.run(globalBean);
//                }
//            }
//        }
//    }

}
