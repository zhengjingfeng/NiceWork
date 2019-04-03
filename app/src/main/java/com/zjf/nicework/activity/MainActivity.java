package com.zjf.nicework.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjf.nicework.R;
import com.zjf.nicework.utils.JniUtil;
import com.zjf.nicework.utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author zhengjingfeng
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<String> list = new ArrayList<>();
    private Set<Integer> set = new HashSet<>();
    private Map<Integer, String> map = new TreeMap<>();
    private TextView tv;
    private ImageView iv;

    private Stack<Integer> stack = new Stack<>();

    /**
     * Collections是一个集合工具类
     */
    private List synList = Collections.synchronizedList(new ArrayList<String>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initView() {
        tv = findViewById(R.id.sample_text);
        iv = findViewById(R.id.iv_pic);
        int sum = JniUtil.getInstance().sum(2, 3);
        String realSum = sum + "";
        tv.setText(realSum);
    }

    private void initData() {
        int result = diGui(4);
        LogUtils.d(TAG, "result:" + result);
        try {
            //输入流：文件读到内存 输出流：内存读到文件
            File file = new File(Environment.getExternalStoragePublicDirectory("Pictures"), "cs.jpg");
            InputStream in = new FileInputStream(file);

            File newFile = new File(getFilesDir(), "csgo.jpg");
            OutputStream out = new FileOutputStream(newFile);
            byte[] bytes = new byte[1024];

            int len;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }

            InputStream in2 = new FileInputStream(newFile);
            Bitmap bitmap = BitmapFactory.decodeStream(in2);
            iv.setImageBitmap(bitmap);

            in.close();
            out.close();

            LogUtils.d(TAG, System.getProperty("file.encoding"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //动态代理
        JackSon jackSon = new JackSon();
        StarProxy starProxy = new StarProxy();
        starProxy.setTarget(jackSon);
        Star star = (Star) starProxy.CreatProxyObj();
        star.sing("beat it");
    }

    public int diGui(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * diGui(n - 1);
        }
    }

    interface Star {
        String sing(String name);

        String dance(String name);
    }

    class JackSon implements Star {

        @Override
        public String sing(String name) {
            LogUtils.d(TAG, name);
            return "唱完";
        }

        @Override
        public String dance(String name) {
            LogUtils.d(TAG, name);
            return "跳完";
        }
    }

    class StarProxy implements InvocationHandler {

        Object target;

        void setTarget(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogUtils.d(TAG, "经纪人收取演出的费用");
            return method.invoke(target, args);
        }

        Object CreatProxyObj() {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }
    }
}