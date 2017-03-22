package com.osmartian.small.app.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.wequick.small.Small;

/**
 * @Author :  walid
 * @Data : 2017-03-09  22:46
 * @Describe : 首页模块fragment
 */
public class MainFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main1, container, false);
        rootView.findViewById(R.id.tvH5).setOnClickListener(v -> Small.openUri("https://github.com/OsMartian/small-frame", getContext()));
        rootView.findViewById(R.id.tvDetail).setOnClickListener(v -> Small.openUri("detail?params=我是参数，从首页传送过来的~", getContext()));
        rootView.findViewById(R.id.tvSub).setOnClickListener(v -> Small.openUri("detail/sub", getContext()));
        return rootView;
    }

}
