package com.syswin.toon.lib.martian.utils.glide;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by walid on 16/8/11.
 * 加载参数
 */

public class LoadParams {

    private static final String ASSETS_HEAD = "file:///android_asset/";

    @IntDef({ImageType.NORMAL, ImageType.CIRCLE, ImageType.ROUND})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageType {
        int NORMAL = 1;
        int CIRCLE = 2;
        int ROUND = 3;
    }

    @IntDef({ResourceType.URL, ResourceType.ASSETS, ResourceType.RES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResourceType {
        int URL = 1;
        int ASSETS = 2;
        int RES = 3;
    }

    @ImageType
    private int imageType = ImageType.NORMAL;
    @ResourceType
    private int resType = ResourceType.URL;
    private float radios;
    @ColorRes
    private int strokeColor;
    private int strokeWidth;
    private String url;
    private int resId;
    @DrawableRes
    private int placeHolder = 0;
    @DrawableRes
    private int error = 0;

    private LoadParams() {
    }

    public static LoadParams get() {
        return new LoadParams();
    }

    public static LoadParams get(String url) {
        return new LoadParams().setUrl(url);
    }

    public int getImageType() {
        return imageType;
    }

    public LoadParams setImageType(int imageType) {
        this.imageType = imageType;
        return this;
    }

    float getRadios() {
        return radios;
    }

    public LoadParams setRadios(float radios) {
        this.radios = radios;
        return this;
    }

    int getStrokeColor() {
        return strokeColor;
    }

    public LoadParams setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    int getStrokeWidth() {
        return strokeWidth;
    }

    public LoadParams setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public String getUrl() {
        if (resType == LoadParams.ResourceType.ASSETS) {
            return url = ASSETS_HEAD + url;
        }
        return url;
    }

    public LoadParams setUrl(String url) {
        this.url = url;
        return this;
    }

    int getResType() {
        return resType;
    }

    public LoadParams setResType(@ResourceType int resType) {
        this.resType = resType;
        return this;
    }

    int getResId() {
        return resId;
    }

    public LoadParams setResId(int resId) {
        this.resId = resId;
        return this;
    }

    int getPlaceHolder() {
        return placeHolder;
    }

    public LoadParams setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
        return this;
    }

    public int getError() {
        return error;
    }

    public LoadParams setError(int error) {
        this.error = error;
        return this;
    }

    boolean isValid() {
        return true;
//        return !TextUtils.isEmpty(url);
    }

}
