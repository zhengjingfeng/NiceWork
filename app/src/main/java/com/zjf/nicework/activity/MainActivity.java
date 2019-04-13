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
import java.lang.reflect.InvocationTargetException;
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

        //将主题添加的背景去掉
        getWindow().setBackgroundDrawable(null);

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
            File file = new File(Environment.getExternalStoragePublicDirectory("Pictures"), "lufei.png");
            InputStream in = new FileInputStream(file);

            File newFile = new File(getFilesDir(), "haizeiwang.png");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(newFile);
            byte[] bytes = new byte[1024];

            int len;

            //从File read进内存，从内存 write到File
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }

            InputStream in2 = new FileInputStream(newFile);

            //Options静态内部类
            BitmapFactory.Options options = new BitmapFactory.Options();

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

        Box<String> box = new Box<>();//泛型类在实例化的时候确定类型

        FanXingDemo fanXingDemo = new FanXingDemo();
        fanXingDemo.do_run(new FanXingDemo().new Dog());
        fanXingDemo.do_run(new FanXingDemo().new Human());
        fanXingDemo.do_run(new FanXingDemo().new Car());

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

    public class Box<T> {
        private T item;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }

    public class FanXingDemo {
        /**
         * TODO：这里的泛型T了解一下，在前面为什么要加上<T>，表明T是一个泛型，不加T，T会被当成确定的数据类型，找不到这个特定的类（T），就会报错
         * @param runner
         * @param <T>
         */
        public <T> void do_run(T runner) {
            LogUtils.d(TAG,"开始跑");
            Class<?> meta = runner.getClass();
            try {
                Method method = meta.getMethod("run");
                method.invoke(runner);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            LogUtils.d(TAG,"结束跑");
        }

        class Dog {
            public void run() {
                LogUtils.d(TAG,"狗，跑");
            }
        }

        class Human {
            public void run() {
                LogUtils.d(TAG,"人，跑");
            }
        }

        class Car {
            public void run() {
                LogUtils.d(TAG,"车，跑");
            }
        }
    }
}