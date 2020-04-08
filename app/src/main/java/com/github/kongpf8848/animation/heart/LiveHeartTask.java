package com.github.kongpf8848.animation.heart;

/**
 * Created by pengf on 2017/1/10.
 */

public class LiveHeartTask implements Runnable
{
    private HeartListener listener;

    public interface HeartListener
    {
         void addHeart();
    }

    public LiveHeartTask(HeartListener listener)
    {
        this.listener=listener;
    }

    @Override
    public void run()
    {
        if(listener!=null)
        {
            listener.addHeart();
        }
    }
}
