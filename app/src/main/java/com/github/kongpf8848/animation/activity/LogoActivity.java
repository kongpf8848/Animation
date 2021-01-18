package com.github.kongpf8848.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.widget.AnimLogoView;

public class LogoActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_logo;
    }

    @Override
    protected int statusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return false;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState) {
        super.onCreateEnd(savedInstanceState);
        AnimLogoView animLogoView = findViewById(R.id.anim_logo);
        animLogoView.addOffsetAnimListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("AnimLogoView", "Offset anim end");
            }
        });
        animLogoView.addGradientAnimListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("AnimLogoView", "Gradient anim end");
                startActivity(SplashActivity.class,true);
            }
        });
        Looper.myQueue().addIdleHandler(() -> {
            animLogoView.startAnimation();
            return false;
        });

    }
}
