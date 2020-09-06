package com.github.kongpf8848.animation;

import android.os.Handler;
import android.os.Looper;

public class MainHandler extends Handler {

    private static volatile MainHandler instance;

    public static MainHandler getInstance(){
        if(instance==null){
            synchronized (MainHandler.class) {
                if (instance == null) {
                    instance= new MainHandler();
                }
            }
        }
        return instance;
    }

    private MainHandler() {
        super(Looper.getMainLooper());
    }

    public void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper()== Looper.myLooper()) {
            runnable.run();
        } else {
           post(runnable);
        }
    }
}
