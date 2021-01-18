package com.github.kongpf8848.animation.activity.tween.translate;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.heart.LiveHeartTaskManager;
import com.github.kongpf8848.animation.heart.LiveHeartTaskManagerThread;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.heart.LiveHeartTask;
import com.github.kongpf8848.animation.widget.HeartLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

/**
 * Created by pengf on 2017/1/2.
 */

public class HeartActivity extends BaseActivity implements LiveHeartTask.HeartListener
{
    private HeartLayout heart_layout;



    private Handler handle=new Handler()
    {
        @Override
        public void handleMessage(Message message)
        {
            heart_layout.addFavor();
        }
    };

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        heart_layout=findViewById(R.id.heart_layout);
        LiveHeartTaskManagerThread downloadTaskManagerThread = new LiveHeartTaskManagerThread();
        new Thread(downloadTaskManagerThread).start();
        startAnimation();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_heart;
    }

    private void startAnimation()
    {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
               // LiveHeartTaskManager.getInstance().addDownloadTask(new LiveHeartTask(HeartActivity.this));
                addHeart();
            }
        },0,100);

    }

    @Override
    public void addHeart() {
       handle.sendEmptyMessageDelayed(0,500);
    }
}
