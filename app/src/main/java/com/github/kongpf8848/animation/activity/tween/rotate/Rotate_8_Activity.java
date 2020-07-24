package com.github.kongpf8848.animation.activity.tween.rotate;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.BaseActivity;


import butterknife.BindView;

/**
 * Created by pengf on 2016/12/29.
 * 图片8旋转动画
 */

public class Rotate_8_Activity extends BaseActivity
{
     @BindView(R.id.iv_loading)
     ImageView iv_loading;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rotate_8;
    }

    @Override
    protected void initData() {
        startAnimation();
    }

    private void startAnimation()
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_loading, "rotationY", 180);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(1000);
        animator.start();

    }
}
