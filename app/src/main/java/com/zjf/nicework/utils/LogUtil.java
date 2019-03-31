package com.zjf.nicework.utils;

import android.util.Log;

import com.zjf.nicework.conf.Constants;

/**
 * Log统一管理类
 *
 * @author DELL
 */
public class LogUtil {

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String TAG = "NFC";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (Constants.BOOLEAN.DEBUG) {
            Log.w(tag, msg);
        }
    }
}