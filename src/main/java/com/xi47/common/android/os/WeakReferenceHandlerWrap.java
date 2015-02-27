package com.xi47.common.android.os;

import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * Created by X on 15/2/27.
 */
public class WeakReferenceHandlerWrap {

    public static class WeakReferenceHandler<T> extends Handler {

        private final WeakReference<T> mReference;

        public WeakReferenceHandler(T reference) {
            mReference = new WeakReference<T>(reference);
        }

        protected final T getReference() {
            if (mReference == null) {
                return null;
            }
            return mReference.get();
        }
    }
}
