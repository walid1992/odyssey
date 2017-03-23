package com.osmartian.small.lib.martian.ui.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.osmartian.small.lib.martian.utils.ScreenUtils;

import java.util.Map;

/**
 * Author   : walid
 * Data     : 2016-09-21  10:35
 * Describe :
 */

public class ProgressBarWebView extends LinearLayout {

    static final String TAG = ProgressBarWebView.class.getSimpleName();

    private ProgressBar progressBar;
    private WebView webView;

    public ProgressBarWebView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressBarWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ProgressBarWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressBarWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context context, AttributeSet attrs) {

        setOrientation(LinearLayout.VERTICAL);

        // 初始化进度条
        if (progressBar == null) {
            progressBar = new ProgressBar(context, attrs);
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transformPx2Current(10, 640));
        addView(progressBar, params);

        // 初始化webview
        if (webView == null) {
            webView = new WebView(context);
        }

        webView.setWebChromeClient(new MartianWebChromeClient(progressBar));
        WebSettings webviewSettings = webView.getSettings();
        // 判断系统版本是不是5.0或之上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //让系统不屏蔽混合内容和第三方Cookie
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
            webviewSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 不支持缩放
        webviewSettings.setSupportZoom(false);

        webviewSettings.setJavaScriptEnabled(true);
        webviewSettings.setDomStorageEnabled(true);

        // 自适应屏幕大小
        webviewSettings.setUseWideViewPort(true);
        webviewSettings.setLoadWithOverviewMode(true);
        // 返回按键监听
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
            }
            return false;
        });

        addView(webView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public WebView getWebView() {
        return webView;
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        webView.loadUrl(url, additionalHttpHeaders);
    }

    public void setWebViewClient(MartianWebViewClient client) {
        webView.setWebViewClient(client);
    }

    public void setWebChromeClient(MartianWebChromeClient chromeClient) {
        webView.setWebChromeClient(chromeClient);
    }

}
