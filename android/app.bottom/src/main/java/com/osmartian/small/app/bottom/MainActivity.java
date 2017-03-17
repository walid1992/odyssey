package com.osmartian.small.app.bottom;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.osmartian.small.app.bottom.bean.TagBean;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private ViewPager viewPager;
    private TagBean[] tagBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            String tags = uri.getQueryParameter("tags");
            if (!TextUtils.isEmpty(tags)) {
                Gson gson = new Gson();
                tagBeans = gson.fromJson(tags, TagBean[].class);
            }
        }
        bindEvent();
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomBar.selectTabAtPosition(position);
            }
        });
        viewPager.setOffscreenPageLimit(3);
    }

    protected void bindEvent() {
        bottomBar.getTabAtPosition(0).setTitle(tagBeans[0].name);
        bottomBar.getTabAtPosition(1).setTitle(tagBeans[1].name);
        bottomBar.getTabAtPosition(2).setTitle(tagBeans[2].name);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_task:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.tab_baby:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.tab_mine:
                        viewPager.setCurrentItem(2, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState();
    }

    private class SectionsPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = Small.createObject("fragment-v4", tagBeans[position].uri, MainActivity.this);
            if (fragment == null) {
                fragment = PlaceholderFragment.newInstance(position + 1);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tagBeans.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tagBeans[position].name;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Hello World from section:" + getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }

}
