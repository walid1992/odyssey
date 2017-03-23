package com.osmartian.small.lib.martian.app;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.osmartian.small.lib.martian.utils.CrashHandler;
import com.osmartian.small.lib.martian.utils.Logger;

import java.io.File;

/**
 * Author   : walid
 * Data     : 2016-09-06  13:21
 * Describe :
 */
public abstract class MartianApp extends Application {

    private static final String LOGPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "haobaobeilog";
    private static MartianApp sContext;

    public static MartianApp instance() {
        return sContext;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        sContext = this;
//        initCrashHandler();
        init();
    }

    protected abstract void init();

    private void initCrashHandler() {
        File file = new File(LOGPATH);
        if (!file.exists()) {
            Logger.d("mkdirs = " + file.mkdirs());
        }
        errorHandlerSwitch(true);
    }

    private void errorHandlerSwitch(boolean canWriteLog) {
        if (!canWriteLog) {
            return;
        }
        // 注册crashHandler
        CrashHandler.getInstance().init(this, LOGPATH, true);
    }

    public void restart() {
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 50, restartIntent);
        exitProgram();
    }

    public void exitProgram() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

}
