package com.xi47.common.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.Locale;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class FileUtil {

    public static final int FILE_TYPE_UNKNOWN = 0;
    public static final int FILE_TYPE_APK = 1;

    public static void openFile(String path, Context context) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        int fileType = getTypeByName(path);
        switch (fileType) {
            case FILE_TYPE_UNKNOWN:
                intent.setDataAndType(uri, "*/*");
                break;

            case FILE_TYPE_APK:
                intent.setDataAndType(uri,
                        "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setClassName("com.android.packageinstaller",
                        "com.android.packageinstaller.PackageInstallerActivity");
                break;

            default:
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getTypeByName(String name) {
        if (TextUtils.isEmpty(name)) {
            return FILE_TYPE_UNKNOWN;
        } else if (name.toLowerCase(Locale.getDefault()).contains(".apk")) {
            return FILE_TYPE_APK;
        } else {
            return FILE_TYPE_UNKNOWN;
        }
    }

    public static String getNameByPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        int index = path.lastIndexOf(File.separator);
        if (index < 0) {
            return null;
        }
        return path.substring(index + 1, path.length());
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        File file = new File(path);
        return file.length();
    }
}
