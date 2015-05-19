package com.xi47.common.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * @author HanXu
 */
public final class ApplicationUtils {

    private boolean mIsInitialled = false;

    private Context mContext = null;
    private PackageInfo mPackageInfo = null;

    private ApplicationUtils() {
        // do nothing.
    }

    public static ApplicationUtils getInstance() {
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
        mIsInitialled = true;
    }

    public boolean isPackageInstalled(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        PackageInfo info = getPackageInfo(packageName);
        return info != null;
    }

    public String getPackageName() {
        PackageInfo info = getPackageInfo();
        if (info == null) {
            return null;
        }
        return info.packageName;
    }

    private PackageInfo getPackageInfo() {
        if (!mIsInitialled) {
            return null;
        }
        if (mPackageInfo != null) {
            return mPackageInfo;
        }
        mPackageInfo = getPackageInfo(mContext.getPackageName());
        return mPackageInfo;
    }

    private PackageInfo getPackageInfo(String packageName) {
        if (!mIsInitialled) {
            return null;
        }
        try {
            PackageManager manager = mContext.getPackageManager();
            return manager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InstanceHolder {

        private static final ApplicationUtils mInstance = new ApplicationUtils();

    }
}
