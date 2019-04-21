package com.zjf.mylibrary;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjf.commonlib.MyLibraryExportService;

/**
 * @author jingfeng
 * @date 2019-04-22 06:32
 * @email 15919820315@163.com
 */

@Route(path = "/mylibrary/MyLibraryService", name = "测试服务")
public class MyLibraryService implements MyLibraryExportService {
    @Override
    public String callMe() {
        return "你 好！";
    }

    @Override
    public void init(Context context) {

    }
}
