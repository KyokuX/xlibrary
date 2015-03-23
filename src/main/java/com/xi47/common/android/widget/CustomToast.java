package com.xi47.common.android.widget;

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

    private Context mContext = null;
    private TextView mTextView = null;
    private Toast mToast = null;

    private CustomToast() {
        //Do nothing.
    }

    public static CustomToast getInstance() {
        return InstanceHolder.mInstance;
    }

    public void showLongToast(int id) {
        if (mContext == null) {
            return;
        }
        showLongToast(mContext.getString(id));
    }

    @SuppressWarnings("WeakerAccess")
    public void showLongToast(String text) {
        show(text, Toast.LENGTH_LONG, false);
    }

    public void showShortToast(int id) {
        if (mContext == null) {
            return;
        }
        showShortToast(mContext.getString(id));
    }

    @SuppressWarnings("WeakerAccess")
    public void showShortToast(String text) {
        show(text, Toast.LENGTH_SHORT, false);
    }

    /**
     * Must call before all other methods.
     *
     * @param context Application context.
     */
    public void init(Context context) {
        if (context == null) {
            return;
        }
        mContext = context;
        mTextView = new TextView(context);
        mTextView.setBackgroundResource(android.R.drawable.toast_frame);
        mToast = new Toast(context);
        mToast.setView(mTextView);
    }

    public void setSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setBackgroundResource(int id) {
        mTextView.setBackgroundResource(id);
    }

    public void showOneTimeToast(int id) {
        if (mContext != null) {
            showOneTimeToast(mContext.getString(id));
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void showOneTimeToast(String text) {
        if (mHistories != null && mHistories.contains(text)) {
            return;
        }
        show(text, Toast.LENGTH_LONG, true);
    }

    private void show(String text, int length, boolean recordHistory) {
        if (recordHistory) {
            if (mHistories == null) {
                //noinspection Convert2Diamond
                mHistories = new ArrayList<String>();
            }
            mHistories.add(text);
        }
        mTextView.setText(text);
        mToast.setDuration(length);
        mToast.show();
    }

    private static class InstanceHolder {
        private static final CustomToast mInstance = new CustomToast();
    }
}
