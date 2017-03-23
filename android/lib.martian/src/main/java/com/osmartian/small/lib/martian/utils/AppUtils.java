package com.osmartian.small.lib.martian.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.osmartian.small.lib.martian.app.MartianApp;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-21  14:02
 * Describe :
 */

public class AppUtils {

    // 获取application中指定的meta-data
    public static String getChannel(@StringRes int resKey) {
        return getChannel(MartianApp.instance().getString(resKey));
    }

    // 获取application中指定的meta-data
    public static String getChannel(String key) {
        if (TextUtils.isEmpty(key)) {
            throw new NullPointerException("key == null!!!");
        }
        String resultData = null;
        Context context = MartianApp.instance();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public static int getPackageHashCode(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        for (PackageInfo packageinfo : apps) {
            String packageName = packageinfo.packageName;
            if (packageName.equals(context.getPackageName())) {
                return packageinfo.signatures[0].hashCode();
            }
        }
        return 0;
    }

}
