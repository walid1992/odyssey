package com.osmartian.small.app.home;

import android.app.ProgressDialog;

import com.osmartian.small.lib.martian.mvp.MartianActivity;
import com.osmartian.small.lib.martian.mvp.MartianPersenter;
import com.umeng.analytics.MobclickAgent;

/**
 * Author   : walid
 * Data     : 2016-08-31  16:15
 * Describe :
 */

public abstract class BaseActivity<TP extends MartianPersenter> extends MartianActivity<TP> {

    protected ProgressDialog progressDialog;

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

}
