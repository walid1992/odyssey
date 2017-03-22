package com.osmartian.small.app.home.ui.page;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.osmartian.small.app.home.BaseFragment;
import com.osmartian.small.app.home.R;
import com.osmartian.small.app.home.bean.server.NearbyBean;
import com.osmartian.small.app.home.constants.EnumConst;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:29
 * Describe :
 */

public class NearbyFragment extends BaseFragment<NearbyPresenter> implements INearbyView {

    public static final String TYPE = "TYPE";

    EasyRecyclerView rvNearby;

    private NearbyListAdapter mNearbyListAdapter;

    public static NearbyFragment newInstance(@EnumConst.OrderType int orderType) {
        Bundle args = new Bundle();
        args.putInt(TYPE, orderType);
        NearbyFragment fragment = new NearbyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootLayoutRes() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected NearbyPresenter createPresenter() {
        return new NearbyPresenter(this);
    }

    @Override
    protected void initViewsAndEvents() {
        rvNearby = viewHolder.getView(R.id.rvNearby);
    }

    @Override
    protected void initData() {
        int type = getArguments().getInt(TYPE);
        rvNearby.setLayoutManager(new LinearLayoutManager(activity));
        rvNearby.setRefreshListener(() -> presenter.loading(type, false));
        mNearbyListAdapter = new NearbyListAdapter(activity, () -> presenter.loadMore());
        rvNearby.addItemDecoration(new DividerDecoration(getResources().getColor(R.color.col_bbbbbb), 1));
        rvNearby.setAdapter(mNearbyListAdapter);
        presenter.loading(type, true);
    }

    @Override
    public void loadingNearbyList(List<NearbyBean> nearbyBeanList) {
        rvNearby.setRefreshing(false);
        mNearbyListAdapter.clear();
        mNearbyListAdapter.addAll(nearbyBeanList);
    }

    @Override
    public void addMoreNearbyList(List<NearbyBean> nearbyBeanList) {
        rvNearby.setRefreshing(false);
        mNearbyListAdapter.addAll(nearbyBeanList);
    }

    @Override
    public void showNoMore() {
        mNearbyListAdapter.pauseMore();
    }

    @Override
    public void setRefreshing(boolean b) {
        rvNearby.setRefreshing(b);
    }

}