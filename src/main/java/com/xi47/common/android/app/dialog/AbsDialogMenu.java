package com.xi47.common.android.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;

/**
 * @author HanXu
 */
public abstract class AbsDialogMenu extends Dialog implements ITVMenu {

    public AbsDialogMenu(Context context) {
        super(context);
    }

    public AbsDialogMenu(Context context, int theme) {
        super(context, theme);
    }

    public AbsDialogMenu(Context context, boolean cancelable,
                         OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                if (isShowing()) {
                    dismiss();
                }
                return true;

            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        View currentFocusView = getCurrentFocus();
        if (currentFocusView == null) {
            return super.onKeyDown(keyCode, event);
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (currentFocusView.getNextFocusUpId() < 0) {
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (currentFocusView.getNextFocusLeftId() < 0) {
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (currentFocusView.getNextFocusRightId() < 0) {
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (currentFocusView.getNextFocusDownId() < 0) {
                    return true;
                }
                break;

            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
