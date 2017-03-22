package com.osmartian.small.app.home;

import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.lib.martian.mvp.IModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author   : walid
 * Data     : 2016-09-18  17:10
 * Describe :
 */

class MineTaskModel implements IModel {

    private int taskType;
    private List<UserTaskItemModel> mTaskInfoVos = new LinkedList<>();

    List<UserTaskItemModel> getTaskInfoVos() {
        return mTaskInfoVos;
    }

    void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    Observable<List<UserTaskItemModel>> userList(int start, int size) {
        return Observable.create(new Observable.OnSubscribe<List<UserTaskItemModel>>() {
            @Override
            public void call(Subscriber<? super List<UserTaskItemModel>> subscriber) {
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            if (start == 0) {
                                mTaskInfoVos.clear();
                            }
                            List<UserTaskItemModel> userTaskItemModels = new ArrayList<>();
                            userTaskItemModels.add(new UserTaskItemModel());
                            userTaskItemModels.add(new UserTaskItemModel());
                            userTaskItemModels.add(new UserTaskItemModel());
                            userTaskItemModels.add(new UserTaskItemModel());
                            userTaskItemModels.add(new UserTaskItemModel());
                            mTaskInfoVos.addAll(userTaskItemModels);
                            subscriber.onNext(userTaskItemModels);
                        });
//                TaskApiService.userList(taskType, start, size, new IHttpCallback<List<UserTaskItemModel>>() {
//                    @Override
//                    public void onNext(List<UserTaskItemModel> datas) {
//                        if (start == 0) {
//                            mTaskInfoVos.clear();
//                        }
//                        mTaskInfoVos.addAll(datas);
//                        subscriber.onNext(datas);
//                    }
//
//                    @Override
//                    public void onError(int code, String message) {
//                        subscriber.onError(new Throwable(message));
//                    }
//                });
            }
        });
    }

}
