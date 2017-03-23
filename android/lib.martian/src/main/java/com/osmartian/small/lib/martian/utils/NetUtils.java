package com.osmartian.small.lib.martian.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.osmartian.small.lib.martian.app.MartianApp;

/**
 * Author   : roy
 * Data     : 2016-08-29  16:03
 * Describe :
 */

public class NetUtils {

    /**
     * Checked the network connect status.
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MartianApp.instance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check WIFI connection.
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) MartianApp.instance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && ConnectivityManager.TYPE_WIFI == info.getType());
    }

    /**
     * Get Current Network Type
     */
    public static String getNetworkType() {
        ConnectivityManager cm = (ConnectivityManager) MartianApp.instance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info == null ? "None" : (ConnectivityManager.TYPE_WIFI == info.getType() ? "Wifi" : "Mobile"));
    }

}
