package com.xi47.common.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * @author HanXu
 */
public final class ApplicationUtil {

    private boolean mIsInited = false;

    private Context mContext = null;
    private PackageInfo mPackageInfo = null;

    private ApplicationUtil() {
        // do nothing.
    }

    public static ApplicationUtil getInstance() {
        return InstanceHolder.mInstance;
    }

    public int getVerCode() {
        PackageInfo info = getPackageInfo();
        return info == null ? -1 : info.versionCode;
    }

    public String getVerName() {
        PackageInfo info = getPackageInfo();
        return info == null ? null : info.versionName;
    }

    public void init(Context context) {
        mContext = context;
        mIsInited = true;
    }

    public boolean isPackageInstalled(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        PackageInfo info = getPackageInfo(packageName);
        return info != null;
    }

    private PackageInfo getPackageInfo() {
        if (!mIsInited) {
            return null;
        }
        if (mPackageInfo != null) {
            return mPackageInfo;
        }
        mPackageInfo = getPackageInfo(mContext.getPackageName());
        return mPackageInfo;
    }

    private PackageInfo getPackageInfo(String packageName) {
        if (!mIsInited) {
            return null;
        }
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InstanceHolder {

        private static final ApplicationUtil mInstance = new ApplicationUtil();

    }
}
