package com.xi47.common.java.util;

import android.text.TextUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Random;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class StringUtils {

    public static final char SEPARATOR_URL_PARAM_APPEND = '&';
    public static final char SEPARATOR_URL_PARAM_EQUALS = '=';

    public static final char[] LETTERS_LOWERCASE = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String getPercentString(long current, long total) {
        if (total < 1 || current < 0) {
            return "0 %";
        }
        if (current > total) {
            return "100 %";
        }
        return current * 100 / total + " %";
    }

    public static String MD5(String baseString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(baseString.getBytes(Charset.defaultCharset()));
            if (bytes == null) {
                return null;
            }
            StringBuilder hexBuilder = new StringBuilder();
            for (byte b : bytes) {
                hexBuilder.append(Character.forDigit((b & 0XF0) >> 4, 16));
                hexBuilder.append(Character.forDigit((b & 0X0F), 16));
            }
            return hexBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String randomPackageName() {
        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder packageName = new StringBuilder();
        packageName.append("com.");
        packageName.append(getRandomLetter(5));
        packageName.append('.');
        packageName.append(getRandomLetter(10));
        return packageName.toString();
    }

    public static String getRandomLetter(int length) {
        return getRandomString(length, LETTERS_LOWERCASE);
    }

    public static String getRandomString(int length, @SuppressWarnings("SameParameterValue") char[] source) {
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
        int start = firstIndex + 3;
        int end = firstSeparatorIndex > 0 ? firstSeparatorIndex : url.length() - 1;
        if (start < 0 || start > end || end > url.length()) {
            return null;
        }
        return url.substring(start, end);
    }

    public static String getBaseUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        int paramStartIndex = url.indexOf('?');
        if (paramStartIndex < 0) {
            return url;
        }
        return url.substring(0, paramStartIndex);
    }
}
