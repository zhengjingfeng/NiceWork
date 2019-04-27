package com.zjf.nicework;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.zjf.nicework.bean.DaoMaster;
import com.zjf.nicework.bean.DaoSession;
import com.zjf.nicework.conf.Constants;

/**
 * @author jingfeng
 * @date 2019/4/12 05:51
 * @email 15919820315@163.com
 */
public class MyApplication extends Application {

    public static MyApplication instance;

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private SQLiteDatabase mDatabase;

    @Override

    public void onCreate() {
        super.onCreate();

        instance = this;

        initLeakCanary();

        initARouter();

        setDataBase();

    }

    private void setDataBase() {
        SqliteHelp help = new SqliteHelp(this, "nicework.db", null);
        SQLiteDatabase db = help.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
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