package com.osmartian.small.app.weex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.osmartian.small.lib.weex.page.WxBaseFragment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : walid
 * @Data : 2017-03-14  16:07
 * @Describe : Mine Weex Fragment
 */
public class MainFragment extends WxBaseFragment {

    private FrameLayout mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        View rootView = inflater.inflate(R.layout.act_wxindex, container, false);
        mContainer = (FrameLayout) rootView.findViewById(R.id.container);
        WXSDKInstance wxsdkInstance = new WXSDKInstance(context);
        wxsdkInstance.registerRenderListener(this);
        Map<String, Object> options = new HashMap<>();

        Uri uri = Uri.parse(String.valueOf(getArguments().get("uri")));
        String url = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("weex page url isn't empty !!!");
        }
        options.put(WXSDKInstance.BUNDLE_URL, url);
        wxsdkInstance.renderByUrl("WxIndex", url, options, null, WXRenderStrategy.APPEND_ASYNC);
        return rootView;
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        mContainer.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
