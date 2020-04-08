package com.github.kongpf8848.animation;

import android.app.Application;
import android.content.Context;

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
    }
}
