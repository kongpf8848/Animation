package com.github.kongpf8848.animation.activity.tween;

import android.content.Intent;
import android.view.View;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.tween.rotate.Rotate_8_Activity;

import butterknife.OnClick;


/**
 * Created by pengf on 2016/12/29.
 * 旋转动画
 */

public class TweenRotateActivity extends BaseActivity
{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_rotate;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view) //图片8旋转动画
    {
        Intent intent=new Intent(this, Rotate_8_Activity.class);
        startActivity(intent);

    }


}
