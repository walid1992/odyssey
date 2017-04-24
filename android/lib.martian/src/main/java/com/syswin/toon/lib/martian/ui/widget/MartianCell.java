package com.syswin.toon.lib.martian.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.walid.autolayout.view.AutoRelativeLayout;
import com.syswin.toon.lib.martian.R;
import com.syswin.toon.lib.martian.utils.glide.GlideUtils;
import com.syswin.toon.lib.martian.utils.glide.LoadParams;
import com.syswin.toon.lib.martian.vh.MartianViewHolder;

/**
 * Author   : walid
 * Data     : 2016-08-23  11:56
 * Describe : common cell 封装
 */
public class MartianCell extends AutoRelativeLayout {

    private MartianViewHolder viewHolder;

    public MartianCell(Context context) {
        super(context);
    }

    public MartianCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new MartianViewHolder(inflater.inflate(R.layout.widget_common_cell, this));
        initStyle(context, attrs);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MartianCell);
        String leftItem = a.getString(R.styleable.MartianCell_CommonCell_tv_left_item);
        String rightItem = a.getString(R.styleable.MartianCell_CommonCell_tv_right_item);
        a.recycle();
        setTvLeftItem(leftItem);
        setTvRightItem(rightItem);
    }

    public void setTvLeftItem(String content) {
        viewHolder.setText(R.id.tv_left_item, content);
    }

    public void setTvRightItem(String content) {
        viewHolder.setText(R.id.tv_right_item, content);
        viewHolder.setVisible(R.id.tv_right_item, !TextUtils.isEmpty(content));
    }

    public void setIvRightItem(String url) {
        GlideUtils.load(getContext(), viewHolder.getView(R.id.iv_right_item), LoadParams.get(url)
                .setStrokeColor(Color.parseColor("#dadada"))
                .setError(R.mipmap.ic_place_holder)
                .setStrokeWidth(4));
        viewHolder.setVisible(R.id.iv_right_item, true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        viewHolder.setVisible(R.id.iv_right_arrow, enabled);
    }
}
