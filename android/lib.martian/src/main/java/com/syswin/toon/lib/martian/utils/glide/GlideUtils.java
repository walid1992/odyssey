package com.syswin.toon.lib.martian.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.syswin.toon.lib.martian.utils.Logger;

/**
 * Created by walid on 16/8/11.
 * glide 图片加载
 */

public class GlideUtils {

    public static void load(Context context, ImageView view, LoadParams loadParams) {
        if (context == null || view == null || !loadParams.isValid()) {
            Logger.d("glide load is invalid");
            return;
        }
        loadForRequestManager(Glide.with(context), view, loadParams, genTransformation(context, loadParams));
    }

    public static void load(Activity activity, ImageView view, LoadParams loadParams) {
        if (activity == null || view == null || !loadParams.isValid()) {
            Logger.d("glide load is invalid ");
            return;
        }
        loadForRequestManager(Glide.with(activity), view, loadParams, genTransformation(activity, loadParams));
    }

    public static void load(Fragment fragment, ImageView view, LoadParams loadParams) {
        if (fragment == null || view == null || !loadParams.isValid()) {
            Logger.d("glide load is invalid");
            return;
        }
        loadForRequestManager(Glide.with(fragment), view, loadParams, genTransformation(fragment.getActivity(), loadParams));
    }

    private static BitmapTransformation genTransformation(Context context, LoadParams loadParams) {
        BitmapTransformation transformation = null;
        switch (loadParams.getImageType()) {
            case LoadParams.ImageType.CIRCLE:
                Logger.d("CircleTransform");
                transformation = new CircleTransform(context, loadParams.getStrokeColor(), loadParams.getStrokeWidth());
                break;
            case LoadParams.ImageType.ROUND:
                Logger.d("RoundTransform");
                transformation = new RoundTransform(context, loadParams.getRadios(), loadParams.getStrokeColor(), loadParams.getStrokeWidth());
                break;
        }
        return transformation;
    }

    private static void loadForRequestManager(RequestManager rm, ImageView view, LoadParams loadParams, BitmapTransformation transformation) {
        DrawableTypeRequest request;
        if (loadParams.getResType() == LoadParams.ResourceType.RES) {
            request = rm.load(loadParams.getResId());
        } else {
            request = rm.load(loadParams.getUrl());
            Logger.d("url = " + loadParams.getUrl());
        }
        request.placeholder(loadParams.getPlaceHolder());
        request.error(loadParams.getError());
        if (transformation != null) {
            request.transform(transformation);
        }
        request.crossFade();
        request.into(view);
    }

}
