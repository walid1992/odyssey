package com.osmartian.small.app.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.app.home.constants.EnumConst;
import com.osmartian.small.lib.martian.utils.ResUtils;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:29
 * Describe :
 */

public class MineTaskFragment extends BaseFragment<MineTaskPresenter> implements IMineTaskView {

    public static final String TASK_TYPE = "TASK_TYPE";

    EasyRecyclerView rvAgent;

    private MineTaskListAdapter mAgentListAdapter;

    public static MineTaskFragment newInstance(@EnumConst.OrderType int orderType) {
        Bundle args = new Bundle();
        args.putInt(TASK_TYPE, orderType);
        MineTaskFragment fragment = new MineTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootLayoutRes() {
        return R.layout.fragment_mine_task;
    }

    @Override
    protected MineTaskPresenter createPresenter() {
        return new MineTaskPresenter(this);
    }

    @Override
    protected void initViewsAndEvents() {
        rvAgent = viewHolder.getView(R.id.rvTask);
    }

    @Override
    protected void initData() {
        int taskType = getArguments().getInt(TASK_TYPE);
        rvAgent.setLayoutManager(new LinearLayoutManager(activity));
        rvAgent.setRefreshListener(() -> presenter.loading(taskType, false));
        mAgentListAdapter = new MineTaskListAdapter(activity, () -> presenter.loadMore());
//        rvAgent.addItemDecoration(new DividerDecoration(ResUtils.getColor(R.color.col_bbbbbb), 1));
        rvAgent.setAdapter(mAgentListAdapter);
        presenter.loading(taskType, true);
    }

    @Override
    public void loadingTaskList(List<UserTaskItemModel> userTaskItemModels) {
        rvAgent.setRefreshing(false);
        mAgentListAdapter.clear();
        mAgentListAdapter.addAll(userTaskItemModels);
    }

    @Override
    public void addMoreTaskList(List<UserTaskItemModel> userTaskItemModels) {
        rvAgent.setRefreshing(false);
        mAgentListAdapter.addAll(userTaskItemModels);
    }

    @Override
    public void showNoMore() {
        mAgentListAdapter.pauseMore();
    }

    @Override
    public void setRefreshing(boolean b) {
        rvAgent.setRefreshing(b);
    }

}