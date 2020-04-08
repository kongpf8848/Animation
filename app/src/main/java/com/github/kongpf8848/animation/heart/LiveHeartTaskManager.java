package com.github.kongpf8848.animation.heart;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by pengf on 2017/1/10.
 */

public class LiveHeartTaskManager
{
    private static LiveHeartTaskManager downloadTaskMananger;
    private LinkedList<LiveHeartTask> downloadTasks;
    private Set<String> taskIdSet;

    private LiveHeartTaskManager() {

        downloadTasks = new LinkedList<LiveHeartTask>();
        taskIdSet = new HashSet<String>();

    }

    public static synchronized LiveHeartTaskManager getInstance() {
        if (downloadTaskMananger == null)
        {
            downloadTaskMananger = new LiveHeartTaskManager();
        }
        return downloadTaskMananger;
    }

    public void addDownloadTask(LiveHeartTask downloadTask)
    {
        synchronized (downloadTasks)
        {
            downloadTasks.addLast(downloadTask);
        }
    }

    public LiveHeartTask getDownloadTask()
    {
        synchronized (downloadTasks) {
            if (downloadTasks.size() > 0)
            {
                System.out.println("下载管理器增加下载任务："+"取出任务");
                LiveHeartTask downloadTask = downloadTasks.removeFirst();
                return downloadTask;
            }
        }
        return null;
    }
}
