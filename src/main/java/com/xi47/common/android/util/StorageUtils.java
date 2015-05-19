package com.xi47.common.android.util;

import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import android.os.storage.StorageManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class StorageUtils {

    public static String convertByteToMb(long size) {
        if (size / (1024 * 1024) > 0) {
            float tmpSize = (float) (size) / (float) (1024 * 1024);
            DecimalFormat df = new DecimalFormat("#.##");
            return "" + df.format(tmpSize) + "MB";
        } else if (size / 1024 > 0) {
            return "" + (size / (1024)) + "KB";
        } else
            return "" + size + "B";
    }

    public static long getAvailableSize(String path) {
        StatFs statFs = new StatFs(path);
        if (Build.VERSION.SDK_INT > 17) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } else {
            return ((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize());
        }
    }

    public static String getAvailableStoragePath(Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String path = "/sdcard";
        if (getAvailableSize(path) > 0) {
            return path;
        }
        StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Method getVolumePathsMethod = manager.getClass().getMethod("getVolumePaths");
        String[] paths = (String[]) getVolumePathsMethod.invoke(manager);
        for (String p : paths) {
            if (getAvailableSize(p) > 0) {
                return p;
            }
        }
        return null;
    }
}
