package com.github.kongpf8848.animation.activity.tween;

import android.view.View;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.tween.translate.FidoActivity;
import com.github.kongpf8848.animation.activity.tween.translate.HeartActivity;
import com.github.kongpf8848.animation.activity.tween.translate.ShakeActivity;

import butterknife.OnClick;

/**
 * Created by jack on 2016/9/16.
 */
public class TweenTranslateActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_translate;
    }

    @OnClick(R.id.button1)
    public void onButton1(View view) {
        startActivity(ShakeActivity.class);
    }


    @OnClick(R.id.button2)
    public void onButton2(View view) {
        startActivity(HeartActivity.class);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view) {
        startActivity(FidoActivity.class);
    }


}
