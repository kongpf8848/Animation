package com.github.kongpf8848.animation.activity.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.helper.ViewWrapper;


import butterknife.BindView;
import butterknife.OnClick;

public class SwapActivity extends BaseActivity {

    @BindView(R.id.ll_root)
    LinearLayout ll_root;
    @BindView(R.id.btn_attention)
    Button btn_attention;
    @BindView(R.id.btn_private)
    Button btn_private;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swap;
    }

    @Override
    protected void initData() {
        super.initData();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                int width=ll_root.getWidth();
                float w1=width*61*1.0f/100;
                float w2=width*35*1.0f/100;
                setW(btn_attention,(int)w1);
                setW(btn_private,(int)w2);
                return false;
            }
        });
    }

    private void setW(View view,int width){
        ViewGroup.LayoutParams layoutParams= view.getLayoutParams();
        layoutParams.width=width;
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    @OnClick({R.id.btn_attention,R.id.btn_private})
    public void onBtnClick() {

        final float x1 = btn_attention.getX();
        float y1 = btn_attention.getY();
        float left1=btn_attention.getLeft();
        int w1 = btn_attention.getWidth();
        int h1 = btn_attention.getHeight();
        float transX1 = btn_attention.getTranslationX();
        float x2 = btn_private.getX();
        float y2 = btn_private.getY();
        int w2 = btn_private.getWidth();
        int h2 = btn_private.getHeight();
        float left2=btn_private.getLeft();
        float transX2 = btn_private.getTranslationX();

        Log.d("JACK9", "x1:" + x1 + ",y1:" + y1 + ",w1:" + w1 + ",h1:" + h1);
        Log.d("JACK9", "x2:" + x2 + ",y2:" + y2 + ",w2:" + w2 + ",h2:" + h2);


        ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(btn_attention, View.ALPHA, 1.0f,0.5f,0.0f,0.5f,1.0f);
        alphaAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator translationXAnimator1 = ObjectAnimator.ofFloat(btn_attention, View.TRANSLATION_X, transX1, transX1 + x2-x1);
        ObjectAnimator widthAnimator1 = ObjectAnimator.ofInt(new ViewWrapper(btn_attention), "width", w1, w2);

        ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(btn_private, View.ALPHA, 1.0f,0.5f,0.0f,0.5f,1.0f);
        ObjectAnimator translationXAnimator2 = ObjectAnimator.ofFloat(btn_private, View.TRANSLATION_X, transX2, transX2 + x1-x2+ (w1 - w2));
        ObjectAnimator widthAnimator2 = ObjectAnimator.ofInt(new ViewWrapper(btn_private), "width", w2, w1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.playTogether(alphaAnimator1,translationXAnimator1,widthAnimator1,alphaAnimator2,translationXAnimator2, widthAnimator2);
        animatorSet.start();


    }



}
