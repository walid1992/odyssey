package com.syswin.toon.lib.martian.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * 日期 工具类
 */
public class DateUtils {

    private static final FastDateFormat YYYY_MM_DD_HH_MM_SS = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat YYYY_MM_DD_HH_MM = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
    private static final FastDateFormat MM_DD_HH_MM = FastDateFormat.getInstance("MM-dd HH:mm");
    private static final FastDateFormat HH_MM = FastDateFormat.getInstance("HH:mm");
    private static final FastDateFormat HH_MM_SS = FastDateFormat.getInstance("HH:mm:ss");
    private static final FastDateFormat YYYY_MM_DD = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat MM_DD = FastDateFormat.getInstance("MM-dd");
    private static final FastDateFormat yyyy年MM月dd日 = FastDateFormat.getInstance("yyyy年MM月dd日");

    private static final long MILLIS_IN_ONE_MINUTE = 1000 * 60;
    private static final long MILLIS_IN_ONE_HOUR = MILLIS_IN_ONE_MINUTE * 60;
    private static final long MILLIS_IN_ONE_DAY = MILLIS_IN_ONE_HOUR * 24;
    private static final long MILLIS_IN_THIRTY_DAY = MILLIS_IN_ONE_DAY * 30;

    // 1970_10
    public static String convert2yyyy_MM(long dateMillionSeconds) {
        return convert2yyyy_MM(new Date(dateMillionSeconds));
    }

    // 1970_10
    public static String convert2yyyy_MM(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        FastDateFormat formatter = MM_DD;
        return formatter.format(dateTime);
    }

    // 1970_10_12
    public static String convert2yyyy_MM_dd(long dateMillionSeconds) {
        return convert2yyyy_MM_dd(new Date(dateMillionSeconds));
    }

    // 1970_10_12
    public static String convert2yyyy_MM_dd(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        FastDateFormat formatter = YYYY_MM_DD;
        return formatter.format(dateTime);
    }

    // 1970_10_12 10:20
    public static String convert2yyyy_MM_dd_HH_mm(long dateMillionSeconds) {
        return convert2yyyy_MM_dd_HH_mm(new Date(dateMillionSeconds));
    }

    // 1970_10_12 10:20
    public static String convert2yyyy_MM_dd_HH_mm(Date date) {
        if (date == null) {
            return null;
        }
        FastDateFormat formatter = YYYY_MM_DD_HH_MM;
        return formatter.format(date);
    }

    // 1970_10_12 10:20:20
    public static String convert2yyyy_MM_dd_HH_mm_ss(long dateMillionSeconds) {
        return convert2yyyy_MM_dd_HH_mm_ss(new Date(dateMillionSeconds));
    }

    // 1970_10_12 10:20:20
    public static String convert2yyyy_MM_dd_HH_mm_ss(Date date) {
        FastDateFormat formatter = YYYY_MM_DD_HH_MM_SS;
        return formatter.format(date);
    }

    public static String convert2yyyy年MM月dd日(long dateMillionSeconds) {
        Date dateTime = new Date(dateMillionSeconds);
        FastDateFormat formatter = yyyy年MM月dd日;
        return formatter.format(dateTime);
    }

    public static String format2时_分_秒(Long l) {
        long hour = 0;
        long minute = 0;
        long second = l;
        if (second > 60) {
            minute = second / 60;//取整
            second = second % 60;//取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return hour + "时" + minute + "分" + second + "秒";
    }

}
