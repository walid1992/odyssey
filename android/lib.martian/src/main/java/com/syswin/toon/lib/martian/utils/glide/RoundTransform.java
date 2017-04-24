package com.syswin.toon.lib.martian.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.math.BigDecimal;

/**
 * Author   : walid
 * Data     : 2016-09-06  16:25
 * Describe :
 */

class RoundTransform extends BitmapTransformation {

    private float radius = 0f;
    private int strokeColor;
    private float strokeWidth;

    RoundTransform(Context context, float radius, @ColorRes int strokeColor, float strokeWidth) {
        super(context);
        this.strokeColor = context.getResources().getColor(strokeColor);
        int screenWidth = getScreenWidth(context);
        this.radius = (int) (screenWidth * round(radius / 750, 3));
        this.strokeWidth = (int) (screenWidth * round(strokeWidth / 750, 3));
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);

        // draw source
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);

        // draw stroke
        Paint strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    private int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private double round(Float number, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = null == number ? new BigDecimal("0.0") : new BigDecimal(
                Double.toString(number));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
