package com.github.kongpf8848.animation.activity;

import android.view.View;
import android.widget.FrameLayout;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.property.BallActivity;
import com.github.kongpf8848.animation.activity.property.CommentActivity;
import com.github.kongpf8848.animation.activity.property.PropertyDemoActivity;
import com.github.kongpf8848.animation.activity.property.SwapActivity;

import butterknife.OnClick;


public class PropertyAnimActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim_property;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(PropertyDemoActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(BallActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view)
    {
        startActivity(SwapActivity.class);
    }

    @OnClick(R.id.button4)
    public void onButton4(View view)
    {
        startActivity(CommentActivity.class);
    }

}
