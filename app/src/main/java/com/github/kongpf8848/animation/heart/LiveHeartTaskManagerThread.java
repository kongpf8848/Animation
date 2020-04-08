package com.github.kongpf8848.animation.heart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pengf on 2017/1/10.
 */

public class LiveHeartTaskManagerThread implements Runnable {

    private LiveHeartTaskManager downloadTaskManager;

    // 创建一个可重用固定线程数的线程池
    private ExecutorService pool;
    // 线程池大小
    private final int POOL_SIZE = 5;
    // 轮询时间
    private final int SLEEP_TIME = 1000;
    // 是否停止
    private boolean isStop = false;

    public LiveHeartTaskManagerThread() {
        downloadTaskManager = LiveHeartTaskManager.getInstance();
        pool = Executors.newFixedThreadPool(POOL_SIZE);

    }

    @Override
    public void run() {
        while (!isStop)
        {
            LiveHeartTask downloadTask = downloadTaskManager.getDownloadTask();
            if (downloadTask != null)
            {
                pool.execute(downloadTask);
            }
            else
            {
                try
                {
                    Thread.sleep(SLEEP_TIME);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
        if (isStop)
        {
            pool.shutdown();
        }

    }

    /**
     * @param isStop
     *            the isStop to set
     */
    public void setStop(boolean isStop)
    {
        this.isStop = isStop;
    }
}
