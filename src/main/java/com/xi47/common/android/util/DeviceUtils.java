package com.xi47.common.android.util;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;

/**
 * @author HanXu
 */
public final class DeviceUtils {

    @SuppressWarnings("WeakerAccess")
    public static final int NETWORK_NOT_CONNECTED = 0x0;
    @SuppressWarnings("WeakerAccess")
    public static final int NETWORK_UNKNOWN = 0x1;

    private boolean mIsInitialled = false;

    private Context mContext = null;

    private DeviceUtils() {
        // do nothing.
    }

    public static DeviceUtils getInstance() {
        return InstanceHolder.mInstance;
    }

    public final int getDpi() {
        DisplayMetrics metrics = getDisplayMetrics();
        if (metrics == null) {
            return -1;
        }
        return metrics.densityDpi;
    }

    public final String getDisPlayString() {
        DisplayMetrics metrics = getDisplayMetrics();
        if (metrics == null) {
            return null;
        }
        return "w" + metrics.widthPixels + "_h" + metrics.heightPixels;
    }

    /**
     * @return Point with x equals width and y equals height.
     */
    public final Point getDisPlayPoint() {
        DisplayMetrics metrics = getDisplayMetrics();
        if (metrics == null) {
            return null;
        }
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }

    public final void init(Context context) {
        if (context == null) {
            return;
        }
        mContext = context;
        mIsInitialled = true;
    }

    public final int getNetworkState() {
        if (mContext == null) {
            return NETWORK_NOT_CONNECTED;
        }
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return NETWORK_NOT_CONNECTED;
        }
        // TODO add other case.
        return NETWORK_UNKNOWN;
    }

    public String getMacAddress() {
        if (!mIsInitialled) {
            return null;
        }
        WifiManager manager = (WifiManager) mContext
                .getSystemService(Context.WIFI_SERVICE);
        if (manager == null) {
            return null;
        }
        WifiInfo info = manager.getConnectionInfo();
        if (info == null) {
            return null;
        }
        return info.getMacAddress();
    }

    public boolean isInitialled() {
        return mContext != null;
    }

    private DisplayMetrics getDisplayMetrics() {
        if (!mIsInitialled) {
            return null;
        }
        return mContext.getResources().getDisplayMetrics();
    }

    private static class InstanceHolder {

        private static final DeviceUtils mInstance = new DeviceUtils();

    }
}
