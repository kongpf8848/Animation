package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.lottie.AutoHomeActivity;
import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;

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
}
