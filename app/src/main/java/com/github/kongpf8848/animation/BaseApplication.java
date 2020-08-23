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
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
                Log.d("Crash", "uncaughtException() called with: t = [" + t + "], e = [" + e.getMessage() + "]");
            }
        });

        try {
            System.loadLibrary("tmessages.30");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getContext(){
        return applicationContext;
    }
}
