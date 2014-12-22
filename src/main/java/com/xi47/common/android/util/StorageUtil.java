package com.xi47.common.android.util;

import android.content.Context;
import android.os.StatFs;
import android.os.storage.StorageManager;

import java.lang.reflect.Method;

/**
 * @author HanXu
 */
public final class StorageUtil {

    public static final long getAvailableSize(String path) {
        try {
            StatFs statFs = new StatFs(path);
            return ((long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static final String getAvailableStoragePath(Context context) {
        StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getVolumePathsMethod = manager.getClass().getMethod("getVolumePaths");
            String[] paths = (String[]) getVolumePathsMethod.invoke(manager);
            for (String path : paths) {
                if (getAvailableSize(path) > 0) {
                    return path;
                }
            }
            return context.getFilesDir().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
