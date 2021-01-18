package com.github.kongpf8848.animation.activity.tween.scale;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.widget.ImageView;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.BaseActivity;

import java.util.Random;

import butterknife.BindView;


/**
 * Created by jack on 2016/8/6.
 */
public class WeizhiActivity extends BaseActivity {

    @BindView(R.id.iv_activity_splash_img)
    public ImageView mIvActivitySplashImg;

    @BindView(R.id.iv_activity_splash_pic)
    public ImageView mIvActivitySplashPic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weizhi;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        super.onCreateEnd(savedInstanceState);
        int index = 1 + new Random().nextInt(3);
        int resourceId = getResources().getIdentifier("welcome_icon_" + index, "mipmap", getPackageName());
        this.mIvActivitySplashPic.setImageResource(resourceId);
        this.mIvActivitySplashPic.setAlpha(0.0F);

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                startAnim();
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mIvActivitySplashImg.clearAnimation();
        this.mIvActivitySplashPic.clearAnimation();
    }


    private void startAnim() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this.mIvActivitySplashImg, "alpha", 1.0F, 0.0F);
        animator1.setDuration(1000L);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this.mIvActivitySplashPic, "alpha", 0.0F, 1.0F);
        animator2.setDuration(1000L);

        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.1f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(this.mIvActivitySplashPic, valuesHolder1, valuesHolder2);
        animator3.setDuration(1000L);

        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2).before(animator3);
        set.start();
    }


}
