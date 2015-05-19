package com.xi47.common.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by X on 15/5/5.
 */
public class DataTimeUtils {

    public static String formatTimeByMilliSecond(int milliSeconds, String format) {
        return formatTimeBySecond(milliSeconds / 1000, format);
    }

    public static String formatTimeBySecond(int seconds, String format) {
        int second = seconds % 60;
        int minutes = (seconds / 60) % 60;
        int hours = seconds / 3600;
        StringBuilder formatBuilder = new StringBuilder();
        Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());
        formatBuilder.setLength(0);
        String text = formatter.format(format, hours, minutes, second).toString();
        formatter.close();
        return text;
    }

    public static String formatCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date());
    }
}
