package com.github.kongpf8848.animation.activity.frame;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;
import android.widget.ImageView;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pengf on 2017/1/7.
 */

public abstract class BaseFrameActivity extends BaseActivity {

     @BindView(R.id.anim_iv)
     ImageView imageView;

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        super.onCreateEnd(savedInstanceState);
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                startAnim();
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAnim();
    }

    private void startAnim()
    {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void stopAnim(){
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
    }
}
