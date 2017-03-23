package com.osmartian.small.app.home.ui.page;

import com.osmartian.small.app.home.bean.server.NearbyBean;
import com.osmartian.small.lib.martian.mvp.IView;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:37
 * Describe :
 */

interface INearbyView extends IView {

    void loadingNearbyList(List<NearbyBean> taskInfoVos);

    void addMoreNearbyList(List<NearbyBean> taskInfoVos);

    void showNoMore();

    void setRefreshing(boolean b);

}


