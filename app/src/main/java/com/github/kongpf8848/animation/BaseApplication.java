package com.github.kongpf8848.animation;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by jack on 2016/5/10.
 */
public class BaseApplication extends Application
{
    public static Context applicationContext;
    @Override
    public void onCreate()
    {
        super.onCreate();
        applicationContext=this;

        System.loadLibrary("tmessages.30");
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
                Log.d("Crash", "uncaughtException() called with: t = [" + t + "], e = [" + e.getMessage() + "]");
            }
        });
    }

    public static Context getContext(){
        return applicationContext;
    }
}
