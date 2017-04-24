package com.syswin.toon.lib.martian.utils.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.syswin.toon.lib.martian.app.MartianApp;
import com.syswin.toon.lib.martian.utils.Logger;

import java.io.File;

/**
 * Created by roy on 16/6/28.
 * 文／Marno（简书作者）
 * 原文链接：http://www.jianshu.com/p/46fd1c253701
 */
public class UpdateService {

    private long taskId;
    private DownloadManager downloadManager;
    private String fileName = "水滴互助.apk";

    public void startUpdate(Context context, String url) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir(File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator, fileName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        taskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        MartianApp.instance().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(taskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Logger.d(">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    Logger.d(">>>下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    Logger.d(">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Logger.d(">>>下载完成");
                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + fileName;
                    installAPK(new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
                    Logger.d(">>>下载失败");
                    break;
            }
        }
    }

    //下载到本地后执行安装
    private void installAPK(File file) {
        if (!file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MartianApp.instance().startActivity(intent);
    }

}
