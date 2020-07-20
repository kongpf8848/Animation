package com.github.kongpf8848.animation.activity.property;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.widget.MyImageView;

import butterknife.BindView;
import butterknife.OnClick;


public class PropertyDemoActivity extends BaseActivity {


    @BindView(R.id.iv_boy)
    MyImageView iv_boy;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_property_demo;
    }

    @OnClick(R.id.button1)
    public void onButton1() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_boy, "alpha", 1f, 0f, 1f);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(1000);
        animator.start();

    }

    @OnClick(R.id.button2)
    public void onButton2() {
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iv_boy, pvhY, pvhZ).setDuration(1000);
        objectAnimator.start();

    }

    @OnClick(R.id.button3)
    public void onButton3() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_boy, "translationX", -200, 200, 0);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        ///////////////////////////////////////////////////////
        animator.start();


    }

    @OnClick(R.id.button4)
    public void onButton4() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_boy, "rotation", 0, 360);
        animator.setDuration(1000);
        animator.start();
    }

    @OnClick(R.id.button5)
    public void onButton5() {
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(iv_boy, "aa", 1.0f, 0.0f, 1.0f)
                .setDuration(1000);
        anim.start();
    }

    @OnClick(R.id.button6)
    public void onButton6() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.alpha);
        animator.setTarget(iv_boy);
        animator.start();

    }

    @OnClick(R.id.button7)
    public void onButton7() {
        iv_boy.animate().scaleX(1.1f).scaleY(1.1f).setDuration(1000).start();
    }


}
