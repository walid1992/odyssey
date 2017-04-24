package com.syswin.toon.lib.martian.mvp;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Author   : walid
 * Data     : 2016-09-05  10:56
 * Describe :
 */

public abstract class MartianPersenter<V extends IView, M extends IModel> implements IPresenter {

    protected V iView;
    protected M model;

    private List<Subscription> subscribers = new LinkedList<>();

    public MartianPersenter(V view) {
        this.iView = view;
        model = createModel();
    }

    public <T> void $subScriber(Observable<T> o, Subscriber<T> action1) {
        subscribers.add(o.subscribe(action1));
    }

    public <T> void $subScriber(Observable<T> o, Action1<T> action1) {
        subscribers.add(o.subscribe(action1));
    }

    protected abstract M createModel();

    @Override
    public void detachView() {
        for (Subscription s : subscribers) {
            if (s != null && s.isUnsubscribed()) {
                s.unsubscribe();
            }
        }
        this.iView = null;
    }

}
