package com.bocc.recite.kotlin.ext;

import android.util.Log;

/**
 * LOG工具
 */
public class LogUtil {

    public static final String TAG = "reciteword";

    public static int i(String tag, String msg) {
        if (Log.isLoggable(TAG, Log.INFO)) {
            return Log.i(TAG, tag+":"+msg);
        }
        return 0;
    }

    public static int i(String tag, String msg, Throwable throwable) {
        if (Log.isLoggable(TAG, Log.INFO)) {
            return Log.i(TAG, tag+":"+msg, throwable);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            return Log.d(TAG, tag+":"+msg);
        }
        return 0;
    }

    public static int d(String tag, String msg, Throwable throwable) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            return Log.d(TAG, tag+":"+msg, throwable);
        }
        return 0;
    }


    public static int w(String tag, String msg) {
        if (Log.isLoggable(TAG, Log.WARN)) {
            return Log.w(TAG, tag+":"+msg);
        }
        return 0;
    }

    public static int w(String tag, String msg, Throwable throwable) {
        if (Log.isLoggable(TAG, Log.WARN)) {
            return Log.w(TAG, tag+":"+msg, throwable);
        }
        return 0;
    }

    public static int e(String tag, String msg) {
        if (Log.isLoggable(TAG, Log.ERROR)) {
            return Log.e(TAG, tag+":"+msg);
        }
        return 0;
    }

    public static int e(String tag, String msg, Throwable throwable) {
        if (Log.isLoggable(TAG, Log.ERROR)) {
            return Log.e(TAG, tag+":"+msg, throwable);
        }
        return 0;
    }
}
