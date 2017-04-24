package com.syswin.toon.lib.martian.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syswin.toon.lib.martian.annotation.RegisterEventBus;
import com.syswin.toon.lib.martian.ui.widget.navigationbar.INavigationbar;
import com.syswin.toon.lib.martian.ui.widget.navigationbar.NavigationBar;
import com.syswin.toon.lib.martian.ui.widget.navigationbar.NavigationbarUtils;
import com.syswin.toon.lib.martian.utils.Logger;
import com.syswin.toon.lib.martian.utils.rxjava.RxBindingUtils;
import com.syswin.toon.lib.martian.vh.MartianViewHolder;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.syswin.toon.lib.martian.R;
import com.syswin.toon.lib.martian.utils.eventbus.MartianEvent;
import com.walid.autolayout.utils.AutoUtils;

import java.lang.annotation.Annotation;

import rx.functions.Action1;

public abstract class MartianFragment<TP extends MartianPersenter> extends RxFragment {

    protected final String TAG = getClass().getSimpleName();
    protected Activity activity = null;
    protected Bundle savedInstanceState;
    protected TP presenter;
    private boolean isRegister;
    private View rootView;
    // 好处延迟加载,用时在去findviewbyid,而且内部也做了缓存
    protected MartianViewHolder viewHolder;

    // 是否初始化成功
    private boolean isPrepared;
    // 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        presenter = createPresenter();
        Annotation annotation = getClass().getAnnotation(RegisterEventBus.class);
        if (annotation != null && ((RegisterEventBus) annotation).isRegister()) {
            isRegister = true;
            MartianEvent.register(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getRootLayoutRes(), container, false);
        }
        viewHolder = new MartianViewHolder(rootView);
        AutoUtils.autoInitParams(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndEvents();
        initToolBar(rootView);
    }

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @LayoutRes
    protected abstract int getRootLayoutRes();

    protected abstract TP createPresenter();

    protected abstract void initViewsAndEvents();

    protected abstract void initData();

    private void initToolBar(View rootView) {
        NavigationBar titleBar = (NavigationBar) rootView.findViewById(R.id.navigationbar);
        INavigationbar annotation = getClass().getAnnotation(INavigationbar.class);
        NavigationbarUtils.setting(titleBar, annotation, () -> {
            if (interceptNavigationClick()) {
                return;
            }
            if (activity == null) {
                return;
            }
            activity.finish();
        });
    }

    protected boolean interceptNavigationClick() {
        return false;
    }

    // 按钮click事件
    protected void $clicks(@IdRes int id, Action1<? super Void> onNext) {
        RxBindingUtils.clicks(onNext, viewHolder.getView(id));
    }

    public void finish() {
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    // 第一次fragment可见（进行初始化工作）
    public void onFirstUserVisible() {
        initData();
    }

    // fragment可见（切换回来或者onResume）
    public void onUserVisible() {
        Logger.d("fragment可见（切换回来或者onResume）");
    }

    // 第一次fragment不可见（不建议在此处理事件）
    public void onFirstUserInvisible() {
        Logger.d("第一次fragment不可见（不建议在此处理事件）");
    }

    // fragment不可见（切换掉或者onPause）
    public void onUserInvisible() {
        Logger.d("fragment不可见（切换掉或者onPause）");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeView(rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        activity = null;
        if (isRegister) {
            MartianEvent.unregister(this);
        }
    }

}
