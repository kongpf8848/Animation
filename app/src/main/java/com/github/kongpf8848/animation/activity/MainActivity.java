package com.github.kongpf8848.animation.activity;

import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.viewpager.XhsActivity;
import com.github.kongpf8848.animation.helper.TransitionHelper;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected boolean enableStatusBar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        setupToolbar();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        startActivity(ViewPagerActivity.class);
    }

}
