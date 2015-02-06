package com.xi47.common.java.util;

import android.text.TextUtils;
import android.util.Base64;

import org.apache.http.protocol.HTTP;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HanXu
 */
public final class StringUtil {

    public static char SEPARATOR_URL_PARAM_APPEND = '&';
    public static char SEPARATOR_URL_PARAM_EQUALS = '=';

    public static char[] LETTERS_LOWERCASE = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String urlEncode(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            return URLEncoder.encode(string, HTTP.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

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

    public static String convertByte2MB(long size) {
        if (size / (1024 * 1024) > 0) {
            float tmpSize = (float) (size) / (float) (1024 * 1024);
            DecimalFormat df = new DecimalFormat("#.##");
            return "" + df.format(tmpSize) + "MB";
        } else if (size / 1024 > 0) {
            return "" + (size / (1024)) + "KB";
        } else
            return "" + size + "B";
    }

    public static String getPercentString(long current,
                                          long total) {
        if (total < 1 || current < 0) {
            return "0 %";
        }
        if (current > total) {
            return "100 %";
        }
        return current * 100 / total + " %";
    }

    public static String base64(String baseString) {
        return Base64.encodeToString(baseString.getBytes(), Base64.NO_WRAP);
    }

    public static String MD5(String baseString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(baseString.getBytes());
            if (bytes == null) {
                return null;
            }
            StringBuilder hexBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                hexBuilder.append(Character.forDigit((bytes[i] & 0XF0) >> 4, 16));
                hexBuilder.append(Character.forDigit((bytes[i] & 0X0F), 16));
            }
            return hexBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String randomPackageName() {
        StringBuilder packageName = new StringBuilder();
        packageName.append("com.");
        packageName.append(getRandomLetter(5));
        packageName.append('.');
        packageName.append(getRandomLetter(10));
        return packageName.toString();
    }

    public static String getRandomLetter(int length) {
        return getRandomString(length, LETTERS_LOWERCASE);
    }

    public static String getRandomString(int length, char[] source) {
        if (length < 1 || source == null || source.length == 0) {
            return null;
        }
        StringBuilder string = new StringBuilder();
        Random random = new Random();
        while (length > 0) {
            string.append(source[random.nextInt(source.length)]);
            length--;
        }
        return string.toString();
    }

    public static String getCurrentTimeString() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.HOUR_OF_DAY));
        builder.append(" : ");
        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 10) {
            builder.append("0");
        }
        builder.append(minute);
        return builder.toString();
    }

    public static String getDomainFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        int firstIndex = url.indexOf("://");
        if (firstIndex < 0) {
            return null;
        }
        int firstSeparatorIndex = url.indexOf('/', firstIndex + 3);
        String domain = url.substring(firstIndex + 3, firstSeparatorIndex > 0 ? firstSeparatorIndex : url.length() - 1);
        return domain;
    }

    public static String getBaseUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        int paramStartIndex = url.indexOf("?");
        if (paramStartIndex < 0) {
            return url;
        }
        return url.substring(0, paramStartIndex);
    }
}
