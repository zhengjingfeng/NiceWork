package com.zjf.nicework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zjf.nicework.bean.DaoMaster;

/**
 * @author jingfeng
 * @date 2019-04-26 20:03
 * @email 15919820315@163.com
 */
public class SqliteHelp extends DaoMaster.OpenHelper {


    public SqliteHelp(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}