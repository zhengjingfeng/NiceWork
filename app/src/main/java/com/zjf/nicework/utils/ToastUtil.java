package com.zjf.nicework.utils;

import android.content.Context;

/**
 * @author jingfeng
 * @date 2018/12/5 11:43
 * @email 15919820315@163.com
 */
public class ToastUtil {
    private ToastUtil()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static boolean isShow = true;
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show();
    }
    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show();
    }
    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, duration).show();
    }
    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {
        if (isShow)
            android.widget.Toast.makeText(context, message, duration).show();
    }
}
