package com.osmartian.small.app.home;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.osmartian.small.app.home.bean.server.task.TaskInfoVo;
import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.app.home.ui.adapter.LoadMoreAdapter;
import com.osmartian.small.lib.martian.utils.DateUtils;
import com.osmartian.small.lib.martian.utils.rxjava.RxBindingUtils;
import com.osmartian.small.lib.martian.vh.MartianAdapterViewHolder;

import net.wequick.small.Small;

import java.util.Locale;

import rx.functions.Action1;

/**
 * Author   : walid
 * Data     : 2016-08-24  11:01
 * Describe :
 */
class MineTaskListAdapter extends LoadMoreAdapter<UserTaskItemModel> {

    MineTaskListAdapter(Context context, RecyclerArrayAdapter.OnLoadMoreListener loadMoreListener) {
        super(context, loadMoreListener);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MartianAdapterViewHolder<UserTaskItemModel>(parent, R.layout.item_task) {
            @Override
            public void setData(UserTaskItemModel data) {
                super.setData(data);
                TaskInfoVo taskInfoVo = data.getTaskInfo();
                if (taskInfoVo != null) {
                    String count = String.format(Locale.CHINA, "剩余次数%1$d次", taskInfoVo.getResidueAmount());
                    setText(R.id.tv_residue_degree, count);
                    setText(R.id.tv_task_name, taskInfoVo.getTaskTitle());
                    setText(R.id.tv_money, String.valueOf(taskInfoVo.getCommission()));
                }
                setText(R.id.tv_end_time, DateUtils.convert2yyyy_MM(data.getDisabledTime()));
                RxBindingUtils.clicks(aVoid -> {
                    Small.openUri("weex?url=" + Uri.encode("http://172.31.242.8:12580/dist/weex/views/mine/app.js"), getContext());
                }, itemView);
            }
        };
    }

}
