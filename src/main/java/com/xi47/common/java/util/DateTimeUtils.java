package com.xi47.common.java.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by X on 15/5/5.
 */
public class DateTimeUtils {

    public static final String TAG_HOUR = "%h";
    public static final String TAG_MINUTE = "%m";
    public static final String TAG_SECOND = "%s";
    public static final String TAG_MILLISECOND = "%ms";

    public static String formatDuration(long duration, String format) {
        if (TextUtils.isEmpty(format) || duration < 0) {
            return format;
        }
        String formatedString = new String(format);
        long restDuration = duration;
        if (formatedString.indexOf(TAG_HOUR) > -1) {
            long hour = restDuration / 1000 / 60 / 60;
            formatedString = formatedString.replaceFirst(TAG_HOUR, String.valueOf(hour));
            restDuration -= hour * 1000 *60 * 60;
        }
        if (formatedString.indexOf(TAG_MINUTE) > -1) {
            long minute = restDuration / 1000 / 60;
            formatedString = formatedString.replaceFirst(TAG_MINUTE, String.valueOf(minute));
            restDuration -= minute * 1000 * 60;
        }
        if (formatedString.indexOf(TAG_SECOND) > -1) {
            long second = restDuration / 1000;
            formatedString = formatedString.replaceFirst(TAG_SECOND, String.valueOf(second));
            restDuration -= second * 1000;
        }
        if (formatedString.indexOf(TAG_MILLISECOND) > -1) {
            formatedString = formatedString.replaceFirst(TAG_MILLISECOND, String.valueOf(restDuration));
        }
        return formatedString;
    }
}
