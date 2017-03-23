package com.osmartian.small.lib.martian.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.walid.autolayout.utils.AutoUtils;
import com.osmartian.small.lib.martian.R;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.INavigationbar;
import com.osmartian.small.lib.martian.annotation.RegisterEventBus;
import com.osmartian.small.lib.martian.utils.Logger;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.NavigationbarUtils;
import com.osmartian.small.lib.martian.utils.eventbus.MartianEvent;
import com.osmartian.small.lib.martian.utils.rxjava.RxBindingUtils;
import com.osmartian.small.lib.martian.vh.MartianViewHolder;
import com.osmartian.small.lib.martian.ui.widget.navigationbar.NavigationBar;

import java.lang.annotation.Annotation;

import rx.functions.Action1;

/**
 * Author   : walid
 * Data     : 2016-08-31  16:15
 * Describe :
 */

public abstract class MartianActivity<TP extends MartianPersenter> extends RxAppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected TP presenter;
    private boolean isRegister;
    // 好处延迟加载,用时在去findviewbyid,而且内部也做了缓存
    protected MartianViewHolder viewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        Annotation annotation = getClass().getAnnotation(RegisterEventBus.class);
        if (annotation != null && ((RegisterEventBus) annotation).isRegister()) {
            isRegister = true;
            MartianEvent.register(this);
        }
        init(savedInstanceState);
    }

    protected abstract TP createPresenter();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void bindEvent();

    private void initToolBar() {
        NavigationBar toolbar = (NavigationBar) findViewById(R.id.navigationbar);
        INavigationbar annotation = getClass().getAnnotation(INavigationbar.class);
        NavigationbarUtils.setting(toolbar, annotation, this::finish);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        viewHolder = new MartianViewHolder(getWindow().getDecorView());
        initToolBar();
        bindEvent();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = AutoUtils.genAutoView(name, context, attrs);
        if (view != null) {
            Logger.d(TAG + "AutoLayout ---> " + name);
            return view;
        }
        return super.onCreateView(name, context, attrs);
    }

    // 按钮click事件
    protected void $clicks(@IdRes int id, Action1<? super Void> onNext) {
        RxBindingUtils.clicks(onNext, viewHolder.getView(id));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegister) {
            MartianEvent.unregister(this);
        }
    }
}
