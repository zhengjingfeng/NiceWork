package com.zjf.nicework;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.zjf.nicework.conf.Constants;

/**
 * @author jingfeng
 * @date 2019/4/12 05:51
 * @email 15919820315@163.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();
    }

    public void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initARouter() {
        if (Constants.BOOLEAN.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}