package com.github.kongpf8848.animation.activity.tween.scale;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

import butterknife.BindView;

/**
 * Created by jack on 2016/8/7.
 */
public class QimangxingActivity extends BaseActivity {


    @BindView(R.id.anim_iv)
    ImageView anim_iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scale_qimangxing;
    }

    @Override
    protected void initData() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0F, 1.2F, 1.0F, 1.2F, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        anim_iv.startAnimation(scaleAnimation);
    }
}
