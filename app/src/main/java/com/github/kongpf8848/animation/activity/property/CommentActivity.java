package com.github.kongpf8848.animation.activity.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.CallSuper;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

public class CommentActivity extends BaseActivity {

    private EditText etFlowDetail;
    private ConstraintLayout constraintLayout;
    private boolean anmoIsLoading = false;
    private int defaultEditText = 0, defaultConsoleLayout = 0;
    private int animatorDurtime = 2000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        etFlowDetail = this.findViewById(R.id.et_find_flow_detail);
        constraintLayout =this.findViewById(R.id.csl_find_flow_detail_right_bottom);
        etFlowDetail.post(new Runnable() {
            @Override
            public void run() {
                defaultEditText = etFlowDetail.getWidth();
                defaultConsoleLayout = constraintLayout.getWidth();
            }
        });


        etFlowDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction() && etFlowDetail.getWidth()<constraintLayout.getWidth()){
                    if (anmoIsLoading){
                        return false;
                    }
                    etFlowDetail.setCursorVisible(true);
                    anmoIsLoading = true;
                    etTextToExpand();
                }
                return false;
            }
        });
    }

    private void etTextToExpand() {
        final int[] constrainStart = {defaultConsoleLayout};
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(animatorDurtime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();//0---->1逐渐变大
                float toSmalScale = 1 - currentValue;
                float dist = constrainStart[0]*currentValue;//每次增加的量
                constrainStart[0] = Math.round(constrainStart[0] * toSmalScale);//4舍五入
                constraintLayout.setTranslationX(constraintLayout.getTranslationX() + dist);
                Log.d("JACK","currentValue:"+currentValue+",dist:"+dist);
                //扩大
                LinearLayout.LayoutParams etEditLayoutParams = (LinearLayout.LayoutParams) etFlowDetail.getLayoutParams();
                etEditLayoutParams.width = etEditLayoutParams.width + Math.round(dist);//这里需要对每次增加的量进行四舍五入，因为它会直接影响到我最后的宽度因素
                etFlowDetail.setLayoutParams(etEditLayoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                anmoIsLoading = false;
            }
        });
        valueAnimator.start();
    }


    private void etTextToClose() {
        final int[] constrainStart = {defaultConsoleLayout};
        final int defaultEditWidth = defaultEditText;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(animatorDurtime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();//0----->1 逐渐变大
                float toSmalScale = 1 - currentValue;
                float dist = (constrainStart[0] - (constrainStart[0] * toSmalScale));
                constrainStart[0] = Math.round(constrainStart[0] * toSmalScale);
                constraintLayout.setTranslationX(constrainStart[0] * toSmalScale);
                //缩小
                LinearLayout.LayoutParams etEditLayoutParams = (LinearLayout.LayoutParams) etFlowDetail.getLayoutParams();
                if (constraintLayout.getTranslationX() == 0) {
                    etEditLayoutParams.width = defaultEditWidth;
                } else {
                    etEditLayoutParams.width = etEditLayoutParams.width - Math.round(dist);
                }
                etFlowDetail.setLayoutParams(etEditLayoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                anmoIsLoading = false;
            }
        });
        valueAnimator.start();
    }

    /**
     * 获取点击事件
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {//隐藏软键盘
                if (etFlowDetail.getWidth() > constraintLayout.getWidth() && !anmoIsLoading) {//关闭输入框
                    anmoIsLoading = true;
                    etFlowDetail.setCursorVisible(false);
                    etTextToClose();
                    hideSoftInput(view.getWindowToken());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);

            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void showKeybord(View view) {
        if (etFlowDetail.getWidth() < constraintLayout.getWidth()) {
            if (anmoIsLoading) {
                return;
            }
            etFlowDetail.setCursorVisible(true);
            anmoIsLoading = true;
            etTextToExpand();
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                view.requestFocus();
                manager.showSoftInput(view, 0);
            }
        }
    }
}

