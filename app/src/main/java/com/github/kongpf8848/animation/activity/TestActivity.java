package com.github.kongpf8848.animation.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.evaluator.WidthEvaluator;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.v_top)
    View view_line1;
    @BindView(R.id.v_bottom)
    View view_line2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @OnClick(R.id.button1)
    public void onButton1(View view) {

        view_line1.setPivotX(0);
        view_line1.setPivotY(2);
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(view_line1,"rotation",0f,45f);
        view_line2.setPivotX(0);
        view_line2.setPivotY(0);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(view_line2,"rotation",0f,-45f);

        ValueAnimator objectAnimator1Width=ValueAnimator.ofObject(new WidthEvaluator(view_line1), 45, 51);
        ValueAnimator objectAnimator2Width=ValueAnimator.ofObject(new WidthEvaluator(view_line2), 45, 51);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator1Width,objectAnimator2Width);
        //animatorSet.playTogether(objectAnimator1,objectAnimator2);
        animatorSet.start();
    }

    @OnClick(R.id.button2)
    public void onButton2(View view) {
        view_line1.setPivotX(0);
        view_line1.setPivotY(0);
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(view_line1,"rotation",45f,0f);
        view_line2.setPivotX(0);
        view_line2.setPivotY(0);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(view_line2,"rotation",-45f,0f);

        ValueAnimator objectAnimator1Width=ValueAnimator.ofObject(new WidthEvaluator(view_line1), 51, 45);
        ValueAnimator objectAnimator2Width=ValueAnimator.ofObject(new WidthEvaluator(view_line2), 51, 45);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator1Width,objectAnimator2Width);
        animatorSet.start();

        /**
         *[987,251][1032,257]
         *[987,284][1032,290]
         *
         *[987,253][1023,289]
         *
         * [987,252][1023,288]
         */

    }
}
