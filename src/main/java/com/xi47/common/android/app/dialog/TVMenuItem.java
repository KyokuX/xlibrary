package com.xi47.common.android.app.dialog;

import android.view.View.OnClickListener;

/**
 * @author HanXu
 */
public class TVMenuItem {

    private int mTextId = -1;
    private int mIconId = -1;

    private OnClickListener mListener = null;

    public TVMenuItem(int textId, int iconId, OnClickListener listener) {
        mTextId = textId;
        mIconId = iconId;
        mListener = listener;
    }

    public final int getTextId() {
        return mTextId;
    }

    public final void setTextId(int textId) {
        mTextId = textId;
    }

    public final int getIconId() {
        return mIconId;
    }

    public final void setIconId(int iconId) {
        mIconId = iconId;
    }

    public final OnClickListener getListener() {
        return mListener;
    }

    public final void setListener(OnClickListener listener) {
        mListener = listener;
    }
}
