package com.github.kongpf8848.animation.activity.tween.scale;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jack on 2016/8/5.
 */
public class CupidActivity extends BaseActivity  {
    @BindView(R.id.cupid_result_text)
    TextView cupid_result_text;
    @BindView(R.id.cupid_result)
    LinearLayout cupid_result;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cupid_shoot;
    }

    @OnClick(R.id.img_cupid_shoot)
    public void onShoot() {
        doAnimation();
    }

    private void doAnimation() {
        cupid_result.setVisibility(View.VISIBLE);
        cupid_result_text.setText(R.string.cupid_result_success);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_jiayuan_scale_big);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cupid_result.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cupid_result.setAnimation(animation);

    }

}
