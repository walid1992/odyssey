package com.osmartian.small.lib.martian.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Author   : walid
 * Data     : 2016-09-23  14:33
 * Describe : 跳转系统界面
 */

public class IntentActionUtils {

    public static void startDialActivity(Activity activity, String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
