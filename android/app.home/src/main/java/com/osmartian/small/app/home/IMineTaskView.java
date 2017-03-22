package com.osmartian.small.app.home;

import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.lib.martian.mvp.IView;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:37
 * Describe :
 */

interface IMineTaskView extends IView {

    void loadingTaskList(List<UserTaskItemModel> taskInfoVos);

    void addMoreTaskList(List<UserTaskItemModel> taskInfoVos);

    void showNoMore();

    void setRefreshing(boolean b);

}


