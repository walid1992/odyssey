package com.syswin.toon.app.top;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.syswin.toon.app.top.bean.TagBean;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TagBean[] tagBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            String tags = uri.getQueryParameter("tags");
            if (!TextUtils.isEmpty(tags)) {
                Gson gson = new Gson();
                tagBeans = gson.fromJson(tags, TagBean[].class);
            }
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            UIUtils.alert(MainActivity.this, "Hello!");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = Small.createObject("fragment-v4", tagBeans[position].uri, MainActivity.this);
            if (fragment == null) {
                throw new NullPointerException("uri is invalid !!!");
            }
            Bundle bundle = new Bundle();
            bundle.putString("name", tagBeans[position].name);
            bundle.putString("uri", tagBeans[position].uri);
            fragment.setArguments(bundle);
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

}
