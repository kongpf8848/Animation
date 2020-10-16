package com.github.kongpf8848.animation.activity;

import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.tween.TweenAlphaActivity;
import com.github.kongpf8848.animation.activity.tween.TweenRotateActivity;
import com.github.kongpf8848.animation.activity.tween.TweenScaleActivity;
import com.github.kongpf8848.animation.activity.tween.TweenTranslateActivity;

import butterknife.OnClick;


/**
 * Created by jack on 2016/8/5.
 */
public class TweenAnimActivity extends BaseToolbarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_anim_tween;
    }

    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(TweenAlphaActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(TweenScaleActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view) {
        startActivity(TweenTranslateActivity.class);
    }

    @OnClick(R.id.button4)
    public void onButton4(View view) {
        startActivity(TweenRotateActivity.class);
    }
}
