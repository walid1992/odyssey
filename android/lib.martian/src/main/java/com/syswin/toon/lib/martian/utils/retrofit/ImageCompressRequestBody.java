package com.syswin.toon.lib.martian.utils.retrofit;

import com.syswin.toon.lib.martian.utils.Logger;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Author   : walid
 * Data     : 2016-09-15  15:15
 * Describe :
 */
public class ImageCompressRequestBody extends RequestBody {

    private static final MediaType IMAGE_MIME_TYPE = MediaType.parse("image/jpg");

    private ImageRequestBodyParams bitmapOperation;
    private String imagePath;

    public ImageCompressRequestBody(String imagePath) {
        this.imagePath = imagePath;
        this.bitmapOperation = ImageRequestBodyParams.getDefault();
    }

    public ImageCompressRequestBody(String imagePath, ImageRequestBodyParams bitmapDisposeEntity) {
        this.bitmapOperation = bitmapDisposeEntity;
        this.imagePath = imagePath;
    }

    @Override
    public MediaType contentType() {
        return IMAGE_MIME_TYPE;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        byte[] bytes = BitmapUtils.bitmapDispose(imagePath, bitmapOperation);
        if (bytes != null && bytes.length > 0) {
            Logger.d("Scaled rotated image to update:" + bytes.length + " bytes");
            sink.write(bytes);
        }
    }

}
