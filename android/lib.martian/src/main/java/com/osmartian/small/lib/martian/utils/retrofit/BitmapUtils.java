package com.osmartian.small.lib.martian.utils.retrofit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.osmartian.small.lib.martian.utils.Logger;
import com.osmartian.small.lib.martian.utils.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author   : walid
 * Data     : 2016-09-10  02:00
 * Describe :
 */

public class BitmapUtils {

    public static byte[] bitmapDispose(String imagePath, @NonNull ImageRequestBodyParams bitmapOperation) {
        byte[] bytes;
        Bitmap disposeBitmap = null;
        int rotateDegree = bitmapOperation.getRotateDegree();
        int destHeight = bitmapOperation.getDestHeight();
        int destWidth = bitmapOperation.getDestWidth();
        int maxBytes = bitmapOperation.getMaxBytes();
        int destQuality = bitmapOperation.getDestQuality();
        int scaleMaxeEdgeLenght = bitmapOperation.getScaleMaxEdgeLenght();

        rotateDegree += readPictureDegree(imagePath);
        if (!TextUtils.isEmpty(imagePath)) {
            disposeBitmap = decodeBitmap(imagePath, destWidth, destHeight);
        }

        if (disposeBitmap == null) {
            return null;
        }

        int bitmapWidth = disposeBitmap.getWidth();
        int bitmapHeight = disposeBitmap.getHeight();

        Matrix matrix = new Matrix();

        //旋转
        if (rotateDegree != 0 && bitmapHeight > bitmapWidth) {
            matrix.postRotate(rotateDegree);
        }

        //缩放 对最大压缩边界进行判断
        if (scaleMaxeEdgeLenght > 0) {
            if (bitmapWidth > bitmapHeight) {
                destWidth = scaleMaxeEdgeLenght;
                destHeight = bitmapHeight;
            } else {
                destHeight = scaleMaxeEdgeLenght;
                destWidth = bitmapWidth;
            }
        }

        if (destHeight > 0 && bitmapHeight >= destHeight && destWidth > 0 && bitmapWidth >= destWidth) {
            float scaleW = (float) destWidth / bitmapWidth;
            float scaleH = (float) destHeight / bitmapHeight;
            float scale = scaleW < scaleH ? scaleW : scaleH;
            matrix.postScale(scale, scale);
        }

        disposeBitmap = Bitmap.createBitmap(disposeBitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
        Logger.d("imageToCompress width = " + disposeBitmap.getWidth());
        Logger.d("imageToCompress height = " + disposeBitmap.getHeight());

        // 压缩质量
        while ((bytes = compressImageFileInternal(disposeBitmap, maxBytes, destQuality)) == null) {
            destQuality -= 10;
            if (destQuality < 10) {
                break;
            }
        }
        return bytes;
    }

    // 读取照片exif信息中的旋转角度
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    degree = 0;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.d("degree = " + degree);
        return degree;
    }

    public static Bitmap decodeBitmap(String path, int destWidth, int destHeight) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        //inJustDecodeBounds
        //If set to true, the decoder will return null (no bitmap), but the out…
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, op); //获取尺寸信息
        if (destHeight <= 0) {
            destHeight = 768;
        }
        if (destWidth <= 0) {
            destWidth = 1024;
        }
        //获取比例大小
        int wRatio = (int) Math.ceil(op.outWidth / destWidth);
        int hRatio = (int) Math.ceil(op.outHeight / destHeight);
        //如果超出指定大小，则缩小相应的比例
        if (wRatio > 1 && hRatio > 1) {
            if (wRatio > hRatio) {
                op.inSampleSize = wRatio;
            } else {
                op.inSampleSize = hRatio;
            }
        }
        op.inJustDecodeBounds = false;
        op.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(path, op);
        } catch (OutOfMemoryError e) {
            Logger.e(e.getMessage());
        }
        return bitmap;
    }

    private static byte[] compressImageFileInternal(Bitmap image, int maxBytes, int quality) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            byte[] bytes = baos.toByteArray();
            int length = bytes.length;
            if (length > maxBytes) {
                return null;
            }
            Logger.d("图片大小：" + (length / 1024) + "K " + "质量 " + quality);
            return bytes;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public static Bitmap getBitmapFormView(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        //creates immutable clone
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        //clear drawing cache
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static void saveView2System(Context context, View v) {
        saveBitmap2System(context, getBitmapFormView(v));
    }

    public static void saveBitmap2System(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "HaoBaoBei");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.show("保存图片失败~");
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, "sava photo~");
            ToastUtils.show("保存图片成功~");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.show("保存图片失败~");
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath() + fileName)));
    }

}
