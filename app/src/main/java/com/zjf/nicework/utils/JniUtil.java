package com.zjf.nicework.utils;

/**
 * @author jingfeng
 * @date 2019/3/23 10:19
 * @email 15919820315@163.com
 */
public class JniUtil {

    static {
        System.loadLibrary("native-lib");
    }

    private JniUtil() {

    }

    private static JniUtil instance;

    public static JniUtil getInstance() {

        if (instance == null) {
            synchronized (JniUtil.class) {
                if (instance == null) {
                    instance = new JniUtil();
                }
            }
        }

        return instance;

    }

    public native String stringFromJNI();

    public native int sum(int a, int b);

}
