package com.github.kongpf8848.animation.activity.tween.alpha;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

import butterknife.BindView;

/**
 * Created by pengf on 2016/12/9.
 */

public class TranslateActivity extends BaseActivity {

    @BindView(R.id.augc_img_women)
    ImageView m_augc_img_women;
    @BindView(R.id.augc_img_hello_chinese)
    ImageView m_augc_img_hello_chinese;
    @BindView(R.id.augc_img_men)
    ImageView m_augc_img_men;
    @BindView(R.id.augc_img_hello_english)
    ImageView m_augc_img_hello_english;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_guide_cartoon;
    }


    @Override
    protected void onCreateEnd(Bundle savedInstanceState){

        startAnim();
    }

    private void startAnim() {
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(m_augc_img_women, "alpha", 0f, 1f);
        animation1.setDuration(2000);
        animation1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                m_augc_img_women.setVisibility(View.VISIBLE);
            }
        });

        m_augc_img_hello_chinese.setPivotX(0f);
        m_augc_img_hello_chinese.setPivotY(0f);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f);
        ObjectAnimator animation2 = ObjectAnimator.ofPropertyValuesHolder(m_augc_img_hello_chinese,holderX, holderY);
        animation2.setDuration(1000);
        animation2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                m_augc_img_hello_chinese.setVisibility(View.VISIBLE);
            }
        });

        ObjectAnimator animation3 = ObjectAnimator.ofFloat(m_augc_img_men, "alpha", 0f, 1f);
        animation3.setDuration(2000);
        animation3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                m_augc_img_men.setVisibility(View.VISIBLE);
            }

        });
        ObjectAnimator animation4 = ObjectAnimator.ofPropertyValuesHolder(m_augc_img_hello_english,holderX, holderY);
        animation4.setDuration(1000);
        animation4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                m_augc_img_hello_english.setVisibility(View.VISIBLE);
            }

        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animation1, animation2, animation3, animation4);
        animatorSet.start();

    }
}
