package com.syswin.toon.lib.martian.ui.webview;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Author   : walid
 * Data     : 2016-09-21  10:40
 * Describe :
 */

public class MartianWebChromeClient extends WebChromeClient {

    private ProgressBar mProgressBar;
    private final static int DEF = 95;

    public MartianWebChromeClient(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        if (progress >= DEF) {
            mProgressBar.setVisibility(View.GONE);
        } else {
            if (mProgressBar.getVisibility() == View.GONE) {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            mProgressBar.setProgress(progress);
        }
        super.onProgressChanged(view, progress);
    }
}