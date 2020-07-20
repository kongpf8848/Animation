package com.github.kongpf8848.animation.activity.tween;

import android.content.Intent;
import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.activity.tween.alpha.OtherActivity;
import com.github.kongpf8848.animation.activity.tween.alpha.TranslateActivity;

import butterknife.OnClick;


/**
 * Created by jack on 2016/9/16.
 */
public class TweenAlphaActivity extends BaseActivity
{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_alpha;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view)
    {
        startActivity(OtherActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    @OnClick(R.id.button2)
    public void onButton2(View view)
    {
        startActivity(OtherActivity.class);
        overridePendingTransition(R.anim.my_scale_action, R.anim.fade_out);
    }

    @OnClick(R.id.button3)
    public void onButton3(View view)
    {
        startActivity(OtherActivity.class);
        overridePendingTransition(R.anim.scale_rotate, R.anim.fade_out);
    }

    @OnClick(R.id.button4)
    public void onButton4(View view)
    {
        startActivity(OtherActivity.class);
        overridePendingTransition(R.anim.scale_translate, R.anim.fade_out);
    }

    @OnClick(R.id.button5)
    public void onButton5(View view)
    {
        startActivity(OtherActivity.class);
        overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);
    }

    @OnClick(R.id.button6)
    public void onButton6(View view)
    {
        Intent intent=new Intent(this, TranslateActivity.class);
        startActivity(intent);
    }
}
