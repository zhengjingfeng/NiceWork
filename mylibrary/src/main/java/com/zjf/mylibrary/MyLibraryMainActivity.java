package com.zjf.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


/**
 * @author zhengjingfeng
 */

@Route(path = "/mylibrary/main")
public class MyLibraryMainActivity extends AppCompatActivity {

    @Autowired
    public long key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mylibrary);
        ARouter.getInstance().inject(this);

        initView();
    }

    private void initView() {
        Toast.makeText(this,key1+"",Toast.LENGTH_SHORT).show();
    }
}
