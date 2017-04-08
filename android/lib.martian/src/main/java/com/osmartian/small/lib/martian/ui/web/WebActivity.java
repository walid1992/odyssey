package com.osmartian.small.lib.martian.ui.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.osmartian.small.lib.martian.R;
import com.osmartian.small.lib.martian.mvp.MartianActivity;
import com.osmartian.small.lib.martian.ui.web.bean.WebConfigVo;
import com.osmartian.small.lib.martian.ui.web.bean.WebEvent;
import com.osmartian.small.lib.martian.ui.webview.MartianWebViewClient;
import com.osmartian.small.lib.martian.ui.webview.ProgressBarWebView;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.INavigationbar;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.NavigationBar;
import com.osmartian.small.lib.martian.utils.ActivityUtils;
import com.osmartian.small.lib.martian.utils.eventbus.MartianEvent;
import com.osmartian.small.lib.martian.utils.rxjava.RxBindingUtils;

/**
 * Author   : walid
 * Data     : 2016-09-20  21:59
 * Describe : WebActivity
 */
@INavigationbar(titleText = "网页", showBack = true)
public class WebActivity extends MartianActivity<WebPresenter> implements IWebView {

    private static final String WEB_URL = "WEB_URL";
    private static final String WEB_CONFIG_VO = "WEB_CONFIG_VO";

    @Override
    protected WebPresenter createPresenter() {
        return new WebPresenter(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        initConfig();

        ProgressBarWebView webView = viewHolder.getView(R.id.webView);
        webView.setWebViewClient(new MartianWebViewClient() {
            @Override
            public String onPageError(String url) {
                return "file:///android_asset/fail.html";
            }
        });
        webView.loadUrl(getIntent().getStringExtra(WEB_URL));
    }

    // 设置config
    private void initConfig() {
        WebConfigVo webConfigVo = (WebConfigVo) getIntent().getSerializableExtra(WEB_CONFIG_VO);
        if (webConfigVo != null) {
            NavigationBar titlebar = viewHolder.getView(R.id.navigationbar);

            String title = webConfigVo.getTitle();
            if (!TextUtils.isEmpty(title)) {
                titlebar.setTitle(webConfigVo.getTitle());
            }

            // 设置图片
            if (webConfigVo.getRightSrc() > 0) {
                titlebar.addAction(new NavigationBar.ImageAction(webConfigVo.getRightSrc()) {
                    @Override
                    public void performAction(View view) {
                        RxBindingUtils.clicks(aVoid -> {
                            MartianEvent.post(new WebEvent(WebActivity.this, WebEvent.TYPE.RIGHT, webConfigVo.getTag()));
                        }, view);
                    }
                });
                return;
            }

            // 设置文字
            if (!TextUtils.isEmpty(webConfigVo.getRightText())) {
                titlebar.addAction(new NavigationBar.TextAction(webConfigVo.getRightText(), webConfigVo.getRightTextColor(), true) {
                    @Override
                    public void performAction(View view) {
                        RxBindingUtils.clicks(aVoid -> {
                            MartianEvent.post(new WebEvent(WebActivity.this, WebEvent.TYPE.RIGHT, webConfigVo.getTag()));
                        }, view);
                    }
                });
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ProgressBarWebView progressBarWebView = viewHolder.getView(R.id.webView);
        if (progressBarWebView.getWebView() != null) {
            progressBarWebView.getWebView().destroy();
        }
    }

    public static void startUp(String url) {
        ActivityUtils.jump(WebActivity.class, intent -> {
            intent.putExtra(WEB_URL, url);
        });
    }

    public static void startUp(String url, WebConfigVo webConfigVo) {
        ActivityUtils.jump(WebActivity.class, intent -> {
            intent.putExtra(WEB_URL, url);
            intent.putExtra(WEB_CONFIG_VO, webConfigVo);
        });
    }

}
