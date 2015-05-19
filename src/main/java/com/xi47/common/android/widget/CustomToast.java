package com.xi47.common.android.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HanXu
 */
public class CustomToast {

    private List<String> mHistories = null;

    private Activity mActivity = null;
    private TextView mTextView = null;
    private Toast mToast = null;

    private CustomToast() {
        //Do nothing.
    }

    public static CustomToast getInstance() {
        return InstanceHolder.mInstance;
    }

    public void showLongToast(int id) {
        if (mActivity == null) {
            return;
        }
        showLongToast(mActivity.getString(id));
    }

    @SuppressWarnings("WeakerAccess")
    public void showLongToast(String text) {
        show(text, Toast.LENGTH_LONG, false);
    }

    public void showShortToast(int id) {
        if (mActivity == null) {
            return;
        }
        showShortToast(mActivity.getString(id));
    }

    @SuppressWarnings("WeakerAccess")
    public void showShortToast(String text) {
        show(text, Toast.LENGTH_SHORT, false);
    }

    /**
     * Must call before all other methods.
     *
     * @param activity activity that hold the toast thread.
     */
    public void init(Activity activity) {
        if (activity == null) {
            return;
        }
        mActivity = activity;
        mTextView = new TextView(activity);
        mTextView.setBackgroundResource(android.R.drawable.toast_frame);
        mToast = new Toast(activity);
        mToast.setView(mTextView);
    }

    public void setSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setBackgroundResource(int id) {
        mTextView.setBackgroundResource(id);
    }

    public void showOneTimeToast(int id) {
        if (mActivity != null) {
            showOneTimeToast(mActivity.getString(id));
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void showOneTimeToast(String text) {
        if (mHistories != null && mHistories.contains(text)) {
            return;
        }
        show(text, Toast.LENGTH_LONG, true);
    }

    private void show(final String text, final int length, boolean recordHistory) {
        if (recordHistory) {
            if (mHistories == null) {
                //noinspection Convert2Diamond
                mHistories = new ArrayList<String>();
            }
            mHistories.add(text);
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(text);
                mToast.setDuration(length);
                mToast.show();
            }
        });
    }

    private static class InstanceHolder {
        private static final CustomToast mInstance = new CustomToast();
    }
}
