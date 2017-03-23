package com.osmartian.small.app.home;

import android.app.ProgressDialog;

import com.osmartian.small.lib.martian.mvp.MartianFragment;
import com.osmartian.small.lib.martian.mvp.MartianPersenter;
import com.umeng.analytics.MobclickAgent;

/**
 * Author   : walid
 * Data     : 2016-09-06  21:29
 * Describe :
 */

public abstract class BaseFragment<TP extends MartianPersenter> extends MartianFragment<TP> {

    protected ProgressDialog progressDialog;

    public void showLoading() {
        if (activity == null) {
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
        }
        progressDialog.show();
    }

    public void dismissLoading() {
        if (progressDialog == null) {
            return;
        }
        progressDialog.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

}
