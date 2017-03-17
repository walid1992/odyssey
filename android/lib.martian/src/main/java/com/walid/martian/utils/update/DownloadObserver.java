package com.walid.martian.utils.update;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/**
 * Author   : walid
 * Data     : 2016-08-23  18:42
 * Describe : 下载进度监听
 */

public class DownloadObserver extends ContentObserver {

    private Handler handler;
    private DownloadManager downloadManager;
    private DownloadManager.Query query;

    public DownloadObserver(Context context, Handler handler, long downId) {
        super(handler);
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        query = new DownloadManager.Query().setFilterById(downId);
        this.handler = handler;
    }

    // 每当/data/data/com.android.providers.download/database/database.db变化后，触发onCHANGE，开始具体查询
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        boolean downloading = true;
        while (downloading) {
            Cursor cursor = downloadManager.query(query);
            cursor.moveToFirst();
            int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            int progress = (bytesDownloaded * 100) / bytesTotal;
            cursor.close();
            handler.sendEmptyMessageDelayed(progress, 100);
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                downloading = false;
            }
        }
    }
}