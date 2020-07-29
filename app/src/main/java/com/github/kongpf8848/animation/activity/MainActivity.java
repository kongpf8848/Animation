package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(FrameAnimActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(TweenAnimActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view)
    {
        startActivity(PropertyAnimActivity.class);
    }

    @OnClick(R.id.button4)
    public void onButton4(View view)
    {
        startActivity(LottieAnimActivity.class);
    }

    @OnClick(R.id.button5)
    public void onButton5(View view)
    {
        startActivity(TgsActivity.class);
    }

    @OnClick(R.id.button6)
    public void onButton6(View view)
    {
        startActivity(TestActivity.class);
    }

}
