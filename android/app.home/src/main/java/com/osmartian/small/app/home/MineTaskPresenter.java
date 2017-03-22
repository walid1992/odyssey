package com.osmartian.small.app.home;

import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.lib.martian.mvp.MartianPersenter;
import com.osmartian.small.lib.martian.utils.ListUtils;
import com.osmartian.small.lib.martian.utils.rxjava.SimpleSubscriber;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:36
 * Describe :
 */

class MineTaskPresenter extends MartianPersenter<IMineTaskView, MineTaskModel> {

    MineTaskPresenter(IMineTaskView view) {
        super(view);
    }

    @Override
    protected MineTaskModel createModel() {
        return new MineTaskModel();
    }

    void loading(int taskType, boolean isFirst) {
        model.setTaskType(taskType);
        iView.setRefreshing(isFirst);
        $subScriber(model.userList(0, 10), new SimpleSubscriber<List<UserTaskItemModel>>() {
            @Override
            public void onNext(List<UserTaskItemModel> taskInfoVos) {
                super.onNext(taskInfoVos);
                iView.loadingTaskList(taskInfoVos);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                iView.setRefreshing(false);
            }
        });
    }

    void loadMore() {
        int start = model.getTaskInfoVos().size();
        $subScriber(model.userList(start, 10), new SimpleSubscriber<List<UserTaskItemModel>>() {
            @Override
            public void onNext(List<UserTaskItemModel> taskInfoVos) {
                super.onNext(taskInfoVos);
                if (ListUtils.isEmpty(taskInfoVos)) {
                    iView.showNoMore();
                    return;
                }
                iView.addMoreTaskList(taskInfoVos);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                iView.setRefreshing(false);
            }
        });
    }

}
