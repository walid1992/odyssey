package com.osmartian.small.app.home.ui.page;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.osmartian.small.app.home.R;
import com.osmartian.small.app.home.bean.business.WebBean;
import com.osmartian.small.app.home.bean.server.NearbyBean;
import com.osmartian.small.app.home.ui.adapter.LoadMoreAdapter;
import com.osmartian.small.lib.martian.ui.web.WebActivity;
import com.osmartian.small.lib.martian.ui.web.bean.WebConfigVo;
import com.osmartian.small.lib.martian.utils.JsonUtils;
import com.osmartian.small.lib.martian.utils.glide.GlideUtils;
import com.osmartian.small.lib.martian.utils.glide.LoadParams;
import com.osmartian.small.lib.martian.utils.rxjava.RxBindingUtils;
import com.osmartian.small.lib.martian.vh.MartianAdapterViewHolder;

import net.wequick.small.Small;

import java.text.DecimalFormat;

/**
 * Author   : walid
 * Data     : 2016-08-24  11:01
 * Describe :
 */
class NearbyListAdapter extends LoadMoreAdapter<NearbyBean> {

    NearbyListAdapter(Context context, RecyclerArrayAdapter.OnLoadMoreListener loadMoreListener) {
        super(context, loadMoreListener);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MartianAdapterViewHolder<NearbyBean>(parent, R.layout.item_nearby) {
            @Override
            public void setData(NearbyBean data) {
                super.setData(data);
                GlideUtils.load(getContext(), getView(R.id.iv_logo), LoadParams.get(data.pictureUrl).setError(R.mipmap.ic_launcher));
                setText(R.id.tv_name, data.name);
                setText(R.id.tv_address, data.address);
                DecimalFormat df = new DecimalFormat("######0.00");
                setText(R.id.tv_distance, df.format(data.distance) + "km");
                RxBindingUtils.clicks(aVoid -> {
                    WebBean webBean = new WebBean();
                    webBean.address = data.address;
                    webBean.name = data.name;
                    String url = "http://portal.toon.mobi/transfer/index?code=hGdXnp+n2gJpeWaA4y88TvX3zqIofFuRs7rGjMIKRBgF141ZG/ttSHnsOvPlYILD2bZv1s0H1JNUklb4ZQ8HWJuwfzIZYGybPPELD8fy7VHDBDQGtGj3eghehnGbbYXKDtCd3V8hEncSq0ZsQ/z6U+Vp9i2t4gOn&poi=" + Uri.encode(JsonUtils.toJson(webBean)) + "&userid=432891";
                    WebConfigVo webConfigVo = new WebConfigVo();
                    webConfigVo.setTitle(webBean.name);
                    WebActivity.startUp(url, webConfigVo);
//                    Small.openUri("http://portal.toon.mobi/transfer/index?code=hGdXnp+n2gJpeWaA4y88TvX3zqIofFuRs7rGjMIKRBgF141ZG/ttSHnsOvPlYILD2bZv1s0H1JNUklb4ZQ8HWJuwfzIZYGybPPELD8fy7VHDBDQGtGj3eghehnGbbYXKDtCd3V8hEncSq0ZsQ/z6U+Vp9i2t4gOn&poi=" + Uri.encode(JsonUtils.toJson(webBean)) + "&userid=432891", getContext());
//                    Small.openUri("detail?params=我是参数，从首页传送过来的~", getContext());
//                    Small.openUri("weex?url=" + Uri.encode("http://172.31.242.8:12580/dist/weex/views/mine/app.js"), getContext());
                }, itemView);
            }
        };
    }

}
