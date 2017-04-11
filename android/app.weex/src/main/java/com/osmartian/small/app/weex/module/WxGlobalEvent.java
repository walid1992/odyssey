package com.osmartian.small.app.weex.module;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.osmartian.small.appstub.event.GlobalEvent;
import com.osmartian.small.appstub.event.bean.GlobalBean;
import com.osmartian.small.appstub.event.bean.Key;
import com.osmartian.small.appstub.event.bean.Value;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * @Author : walid
 * @Data : 2017-04-01  09:26
 * @Describe : 用户信息modules
 */
public class WxGlobalEvent extends WXModule {

    // 同步操作 uiThread = false
    // 异步操作 uiThread = true
    @JSMethod(uiThread = false)
    public void post(final JSCallback callback) {
        GlobalEvent.post(new GlobalBean(Key.LOGIN_SUCCESS, new Value(0, "success", "")));
    }

    @JSMethod(uiThread = false)
    public void register(@Nullable final JSCallback callback) {
        GlobalEvent.register(this, Key.LOGIN_SUCCESS).then(res -> {
            if (callback != null) {
                callback.invoke(JSON.toJSONString(res));
            }
        });
    }

}