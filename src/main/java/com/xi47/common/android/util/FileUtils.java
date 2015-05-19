package com.xi47.common.android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class FileUtils {

    public static final int FILE_TYPE_UNKNOWN = 0;
    public static final int FILE_TYPE_APK = 1;

    public static void openFile(String path, Context context) throws ActivityNotFoundException {
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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

    public static boolean saveFile(String path, String content) throws IOException {
        if (TextUtils.isEmpty(path) || TextUtils.isEmpty(content)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffers = new byte[8192];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        while ((bytesRead = byteArrayInputStream.read(buffers, 0, 8192)) != -1) {
            outputStream.write(buffers, 0, bytesRead);
        }
        outputStream.close();
        byteArrayInputStream.close();
        return true;
    }
}
