package com.syswin.toon.app.home.ui.adapter;

import android.content.Context;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.syswin.toon.app.home.R;


/**
 * Author   : walid
 * Data     : 2016-09-10  16:10
 * Describe :
 */

public abstract class LoadMoreAdapter<T> extends RecyclerArrayAdapter<T> {

    public LoadMoreAdapter(Context context, OnLoadMoreListener loadMoreListener) {
        super(context);
        setNoMore(R.layout.judy_item_nomore);
        setMore(R.layout.judy_item_loadmore, loadMoreListener);
    }

}
