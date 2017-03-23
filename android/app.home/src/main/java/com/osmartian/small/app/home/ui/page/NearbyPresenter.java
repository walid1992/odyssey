package com.osmartian.small.app.home.ui.page;

import com.osmartian.small.app.home.bean.server.NearbyBean;
import com.osmartian.small.lib.martian.mvp.MartianPersenter;
import com.osmartian.small.lib.martian.utils.ListUtils;
import com.osmartian.small.lib.martian.utils.rxjava.SimpleSubscriber;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:36
 * Describe :
 */

class NearbyPresenter extends MartianPersenter<INearbyView, NearbyModel> {

    NearbyPresenter(INearbyView view) {
        super(view);
    }

    @Override
    protected NearbyModel createModel() {
        return new NearbyModel();
    }

    void loading(int taskType, boolean isFirst) {
        model.setType(taskType);
        iView.setRefreshing(isFirst);
        $subScriber(model.nearbys(0, 10), new SimpleSubscriber<List<NearbyBean>>() {
            @Override
            public void onNext(List<NearbyBean> taskInfoVos) {
                super.onNext(taskInfoVos);
                iView.loadingNearbyList(taskInfoVos);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                iView.setRefreshing(false);
            }
        });
    }

    void loadMore() {
        int start = model.getNearbyBeans().size();
        $subScriber(model.nearbys(start, 10), new SimpleSubscriber<List<NearbyBean>>() {
            @Override
            public void onNext(List<NearbyBean> taskInfoVos) {
                super.onNext(taskInfoVos);
                if (ListUtils.isEmpty(taskInfoVos)) {
                    iView.showNoMore();
                    return;
                }
                iView.addMoreNearbyList(taskInfoVos);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                iView.setRefreshing(false);
            }
        });
    }

}
