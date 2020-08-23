package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.property.BallActivity;
import com.github.kongpf8848.animation.activity.property.CommentActivity;
import com.github.kongpf8848.animation.activity.property.PropertyDemoActivity;
import com.github.kongpf8848.animation.activity.property.SwapActivity;
import com.github.kongpf8848.animation.activity.viewpager.WeChatActivity;
import com.github.kongpf8848.animation.activity.viewpager.XhsActivity;

import butterknife.OnClick;


public class ViewPagerActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(WeChatActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(XhsActivity.class);
    }



}
