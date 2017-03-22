package com.osmartian.small.lib.martian.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Author   : walid
 * Data     : 2016-10-18  14:59
 * Describe :
 */

public class SystemUtils {

    /**
     * 实现文本复制功能
     */
    public static void copy(Context context, String lable, String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(lable, content));
    }

    /**
     * 实现文本粘贴功能
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return String.valueOf(cmb.getPrimaryClip().getItemAt(0).coerceToText(context));
    }

}
