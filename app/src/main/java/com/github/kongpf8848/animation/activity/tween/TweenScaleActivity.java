package com.github.kongpf8848.animation.activity.tween;

import android.view.View;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.tween.scale.CupidActivity;
import com.github.kongpf8848.animation.activity.tween.scale.QimangxingActivity;
import com.github.kongpf8848.animation.activity.tween.scale.WeizhiActivity;

import butterknife.OnClick;


/**
 * Created by jack on 2016/8/5.
 */
public class TweenScaleActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_scale;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(CupidActivity.class);
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(WeizhiActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view) {
        startActivity(QimangxingActivity.class);
    }




}
