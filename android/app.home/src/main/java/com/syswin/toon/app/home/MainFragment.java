package com.syswin.toon.app.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.syswin.toon.app.home.constants.EnumConst;
import com.syswin.toon.app.home.ui.adapter.FragmentPagerAdapter;
import com.syswin.toon.app.home.ui.page.NearbyFragment;
import com.syswin.toon.lib.martian.mvp.MartianPersenter;
import com.syswin.toon.lib.martian.ui.widget.navigationbar.INavigationbar;

/**
 * Author   : walid
 * Data     : 2016-09-07  00:32
 * Describe :
 */

@INavigationbar(titleText = "首页")
public class MainFragment extends BaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected MartianPersenter createPresenter() {
        return null;
    }

    @Override
    protected int getRootLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViewsAndEvents() {
        tabLayout = viewHolder.getView(R.id.tabLayout);
        viewPager = viewHolder.getView(R.id.viewPager);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager());
        fragmentPagerAdapter.addFragment(NearbyFragment.newInstance(EnumConst.OrderType.ESTATE), "小区");
        fragmentPagerAdapter.addFragment(NearbyFragment.newInstance(EnumConst.OrderType.BUILDING), "写字楼");
        fragmentPagerAdapter.addFragment(NearbyFragment.newInstance(EnumConst.OrderType.SCHOOL), "学校");
        fragmentPagerAdapter.addFragment(NearbyFragment.newInstance(EnumConst.OrderType.SCENIC), "景区");
        fragmentPagerAdapter.addFragment(NearbyFragment.newInstance(EnumConst.OrderType.HOUSE), "新房");
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void initData() {

    }

}
