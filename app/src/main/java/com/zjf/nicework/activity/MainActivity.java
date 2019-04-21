package com.zjf.nicework.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zjf.mylibrary.MyLibraryService;
import com.zjf.nicework.R;
import com.zjf.nicework.utils.JniUtil;
import com.zjf.nicework.utils.LogUtils;
import com.zjf.nicework.utils.ToastUtil;

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
@Route(path = "/app/main")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_PERMISSION_CODE = 100;

    private List<String> list = new ArrayList<>();
    private Set<Integer> set = new HashSet<>();
    private Map<Integer, String> map = new TreeMap<>();
    private TextView tv;
    private ImageView ivPicture;

    private Button btn;

    private Stack<Integer> stack = new Stack<>();

    /**
     * Collections是一个集合工具类
     */
    private List synList = Collections.synchronizedList(new ArrayList<String>());

    @Autowired(name = "/mylibrary/MyLibraryService")
    public MyLibraryService myLibraryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ARouter.getInstance().inject(this);

        String[] periMissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            checkPermission(periMissions);
        }

        //将主题添加的背景去掉
        getWindow().setBackgroundDrawable(null);

        initView();

        initData();

    }

    private void checkPermission(String[] periMissions) {
        requestPermissions(periMissions, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initData();
            }
        }
    }

    private void initView() {
        btn = findViewById(R.id.btn_lufei);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mylibrary/main")
                        .withLong("key1", 666L)
                        .navigation();
            }
        });
        tv = findViewById(R.id.sample_text);
        ivPicture = findViewById(R.id.iv_pic);
        int sum = JniUtil.getInstance().sum(2, 3);
        String realSum = sum + "";
        tv.setText(realSum);
        int result = diGui(4);
        LogUtils.d(TAG, "result:" + result);

        LogUtils.d(TAG, myLibraryService.callMe());

    }

    private void initData() {

        //复制图片，把图片显示在控件上
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

            ivPicture.setImageBitmap(bitmap);

            in.close();
            out.close();

            LogUtils.d(TAG, System.getProperty("file.encoding"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //增加动画效果
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivPicture, "scaleX", 1.0f, 0.4f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                LogUtils.d(TAG, value + "");
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ToastUtil.showShort(MainActivity.this, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ToastUtil.showShort(MainActivity.this, "onAnimationEnd");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                ToastUtil.showShort(MainActivity.this, "onAnimationRepeat");
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ToastUtil.showShort(MainActivity.this, "onAnimationStart!!!!");
            }
        });

        animator.start();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lufei:
                ARouter.getInstance().build("/mylibrary/main").navigation();
                break;
                default:
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
         *
         * @param runner
         * @param <T>
         */
        public <T> void do_run(T runner) {
            LogUtils.d(TAG, "开始跑");
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
            LogUtils.d(TAG, "结束跑");
        }

        class Dog {
            public void run() {
                LogUtils.d(TAG, "狗，跑");
            }
        }

        class Human {
            public void run() {
                LogUtils.d(TAG, "人，跑");
            }
        }

        class Car {
            public void run() {
                LogUtils.d(TAG, "车，跑");
            }
        }
    }

}