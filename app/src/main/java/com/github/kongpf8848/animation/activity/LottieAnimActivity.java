package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.lottie.AutoHomeActivity;
import com.github.kongpf8848.animation.activity.lottie.BossActivity;
import com.github.kongpf8848.animation.activity.lottie.CardActivity;

import butterknife.OnClick;

public class LottieAnimActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lottie;
    }

    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(AutoHomeActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(BossActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view) {
        startActivity(CardActivity.class);
    }
}
