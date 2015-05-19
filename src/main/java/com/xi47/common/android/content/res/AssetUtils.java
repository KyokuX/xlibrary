package com.xi47.common.android.content.res;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author HanXu
 */
public final class AssetUtils {

    private Context mContext = null;

    private AssetUtils() {
        // do nothing.
    }

    public static AssetUtils getInstance() {
        return InstanceHolder.mInstance;
    }

    public String getText(String fileName) {
        return getText(fileName, Charset.defaultCharset());
    }

    public String getText(String fileName, Charset charset) {
        if (mContext == null) {
            throw (new NullPointerException("Must call init() before any other methods."));
        }
        AssetManager manager = mContext.getAssets();
        BufferedReader reader = null;
        try {
            StringBuilder builder = new StringBuilder();
            InputStream stream = manager.open(fileName);
            reader = new BufferedReader(new InputStreamReader(stream, charset));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            stream.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void init(Context context) {
        mContext = context;
    }

    private static class InstanceHolder {

        private static final AssetUtils mInstance = new AssetUtils();
    }
}
