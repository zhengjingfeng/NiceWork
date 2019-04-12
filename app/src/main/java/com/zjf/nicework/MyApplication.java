package com.zjf.nicework;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author jingfeng
 * @date 2019/4/12 05:51
 * @email 15919820315@163.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
