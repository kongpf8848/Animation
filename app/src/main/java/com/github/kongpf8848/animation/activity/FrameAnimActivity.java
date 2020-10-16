package com.github.kongpf8848.animation.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.frame.Frame_1_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_2_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_3_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_4_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_5_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_6_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_7_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_8_Activity;
import com.github.kongpf8848.animation.activity.frame.Frame_9_Activity;

import butterknife.OnClick;

/**
 * Created by jack on 2016/8/5.
 */
public class FrameAnimActivity  extends BaseToolbarActivity
{

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim_frame;
    }


    @OnClick(R.id.button1)
    public void onButton1(View view)
    {
        startActivity(Frame_1_Activity.class);
    }
    @OnClick(R.id.button2)
    public void onButton2(View view)
    {
        startActivity(Frame_2_Activity.class);
    }
    @OnClick(R.id.button3)
    public void onButton3(View view)
    {
        startActivity(Frame_3_Activity.class);
    }
    @OnClick(R.id.button4)
    public void onButton4(View view)
    {
        startActivity(Frame_4_Activity.class);
    }
    @OnClick(R.id.button5)
    public void onButton5(View view)
    {
        startActivity(Frame_5_Activity.class);
    }
    @OnClick(R.id.button6)
    public void onButton6(View view)
    {
        startActivity(Frame_6_Activity.class);
    }
    @OnClick(R.id.button7)
    public void onButton7(View view)
    {
        startActivity(Frame_7_Activity.class);
    }
    @OnClick(R.id.button8)
    public void onButton8(View view)
    {
        startActivity(Frame_8_Activity.class);
    }
    @OnClick(R.id.button9)
    public void onButton9(View view)
    {
        startActivity(Frame_9_Activity.class);
    }
}