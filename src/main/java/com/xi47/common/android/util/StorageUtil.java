package com.xi47.common.android.util;

import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import android.os.storage.StorageManager;

import java.lang.reflect.Method;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class StorageUtil {

    public static long getAvailableSize(String path) {
        try {
            StatFs statFs = new StatFs(path);
            if (Build.VERSION.SDK_INT > 17) {
                return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            } else {
                return ((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getAvailableStoragePath(Context context) {
        StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getVolumePathsMethod = manager.getClass().getMethod("getVolumePaths");
            String[] paths = (String[]) getVolumePathsMethod.invoke(manager);
            for (String path : paths) {
                if (getAvailableSize(path) > 0) {
                    return path;
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
