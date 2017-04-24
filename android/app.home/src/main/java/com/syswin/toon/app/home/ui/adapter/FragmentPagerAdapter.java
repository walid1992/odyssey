package com.syswin.toon.app.home.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-06  14:46
 * Describe :
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private final List<Fragment> fragments = new LinkedList<>();
    private final List<String> fragmentTitles = new LinkedList<>();

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

}
