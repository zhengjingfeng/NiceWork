package com.zjf.nicework.net;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 */
public class ThreadPoolManager {
    private static ThreadPoolManager mIntance;
    private ThreadPoolExecutor executor;

    public static ThreadPoolManager getInstance() {
        if (mIntance == null) {
            synchronized (ThreadPoolManager.class) {
                if (mIntance == null) {
                    mIntance = new ThreadPoolManager();
                }
            }
        }
        return mIntance;
    }

    private ThreadPoolManager() {

        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;

        int maximumPoolSize = corePoolSize;

        /**
         * 创建线程的工厂，设置线程的优先级，group，以及命名
         */


        long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.HOURS;
        executor = new ThreadPoolExecutor(
                //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
                corePoolSize,
                //先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
                maximumPoolSize,
                //表示的是maximumPoolSize当中等待任务的存活时间
                keepAliveTime,
                unit,
                //缓冲队列，用于存放等待任务，Linked的先进先出
                new LinkedBlockingQueue<Runnable>(),
                //创建线程的工厂，默认

                new DefaultThreadFactory(Thread.NORM_PRIORITY, "thread-pool-"),

                //用来对超出maximumPoolSize的任务的处理策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public class DefaultThreadFactory implements ThreadFactory {
        /**
         * 线程池的计数
         */
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        /**
         * 线程的计数
         */
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final ThreadGroup group;
        private final String namePrefix;
        private final int threadPriority;

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;
            this.group = Thread.currentThread().getThreadGroup();
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            t.setPriority(threadPriority);
            return t;
        }
    }

    /**
     * 执行任务  o0
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }

    /**
     * 从线程池中移除任务
     */
    public void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.remove(runnable);
    }
}