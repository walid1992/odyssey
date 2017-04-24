package com.syswin.toon.lib.weex.weex.https;

public interface WXRequestListener {

    void onSuccess(WXHttpTask task);

    void onError(WXHttpTask task);
}
