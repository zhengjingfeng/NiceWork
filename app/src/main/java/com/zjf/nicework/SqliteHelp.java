package com.zjf.nicework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.zjf.nicework.bean.DaoMaster;
import com.zjf.nicework.bean.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author jingfeng
 * @date 2019-04-26 20:03
 * @email 15919820315@163.com
 */
public class SqliteHelp extends DaoMaster.OpenHelper {

    public SqliteHelp(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserDao.class);
    }
}