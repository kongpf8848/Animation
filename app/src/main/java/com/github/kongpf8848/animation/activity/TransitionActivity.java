package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.transition.ShareElementOneActivity;

import butterknife.OnClick;

public class TransitionActivity extends BaseToolbarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transition;
    }

    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(ShareElementOneActivity.class);
    }


}
