package com.github.kongpf8848.animation;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;

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
    }

    public static Context getContext(){
        return applicationContext;
    }
}
