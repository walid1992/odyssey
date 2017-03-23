package com.osmartian.small.lib.martian.ui.web;

import com.osmartian.small.lib.martian.mvp.MartianPersenter;

/**
 * Author   : walid
 * Data     : 2016-09-20  21:59
 * Describe :
 */
class WebPresenter extends MartianPersenter<IWebView,WebModel> {

    WebPresenter(IWebView view) {
        super(view);
    }

    @Override
    protected WebModel createModel() {
        return new WebModel();
    }


}
