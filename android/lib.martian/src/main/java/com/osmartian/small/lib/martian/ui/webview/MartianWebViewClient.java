package com.osmartian.small.lib.martian.ui.webview;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;

/**
 * Author   : walid
 * Data     : 2016-09-21  10:44
 * Describe :
 */

public abstract class MartianWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        view.loadUrl(url, onPageHeaders(url));
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    /**
     * return errorUrl
     */
    public abstract String onPageError(String url);

    /**
     * HttpHeaders
     */
    public Map<String, String> onPageHeaders(String url) {
        return null;
    }

}