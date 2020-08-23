package com.github.kongpf8848.animation.splash;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.github.kongpf8848.animation.BaseApplication;
import com.kongpf.commonhelper.ScreenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SplashAnimation extends Animation {

    private Random random = new Random();

    private int textStart=0;
    private int textEnd=0;
    private int picStart=0;
    private int picEnd=0;

    private SplashView mSplashView;
    public ArrayList<TopView> topViews = new ArrayList<>();

    public static class TopView
    {
        int margin = ScreenHelper.dp2px(BaseApplication.getContext(), 10.0F);
        public View view;
        public float startAlpha;
        public int topMargin;
        FrameLayout.LayoutParams layoutParams;

        public TopView(View view, float alpha)
        {
            this.view = view;
            this.startAlpha = alpha;
            this.layoutParams = ((FrameLayout.LayoutParams)view.getLayoutParams());
            this.topMargin = this.layoutParams.topMargin;
        }

        public void doAnimation(float alpha)
        {
            float half = 0.5F;
            float temp = alpha - this.startAlpha;
            if (temp > half)
            {
                return;
            }

            float result = temp * 2.0F;
            this.view.setAlpha(result);
            this.layoutParams.topMargin = ((int)((1.0F - result) * margin + this.topMargin));

            Log.d("JACK8","topmargin:"+this.layoutParams.topMargin+",alpha:"+alpha);
            this.view.setLayoutParams(this.layoutParams);

        }
    }


    public SplashAnimation(SplashView splashView)
    {
        this.mSplashView=splashView;
        int screenWidth = ScreenHelper.getScreenWidth(BaseApplication.applicationContext);

        int textWidth = this.mSplashView.text.getMeasuredWidth();
        this.textStart = -(textWidth / 2);
        this.textEnd = (screenWidth - textWidth) / 2;

        int picWidth = this.mSplashView.pic.getMeasuredWidth();
        this.picStart = -(picWidth / 2);
        this.picEnd = (screenWidth - picWidth) / 2;
    }

    /**
     * 做透明度和位移动画
     * @param view
     * @param alpha
     * @param start
     * @param end
     */
    private void alphaAnimation1(View view,float alpha,int start,int end)
    {
        if (view== null) {
            return;
        }
        view.setAlpha(alpha);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        layoutParams.rightMargin = ((int)(start + alpha * (end-start)));
        Log.d("JACK8", "alphaAnimation called with:view="+view+", alpha = [" + alpha + "]"+",rightMargin:"+layoutParams.rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 缩放动画
     * @param alpha
     */
    private void scalaAnimation(float alpha)
    {
        if ((this.mSplashView == null) || (this.mSplashView.certificate == null)) return;
        this.mSplashView.certificate.setAlpha(alpha);
        this.mSplashView.certificate.setScaleX(2.0F - alpha);
        this.mSplashView.certificate.setScaleY(2.0F - alpha);
    }

    /**
     * 透明度和位移动画
     * @param alpha
     */
    private void alphaAnimation2(float alpha)
    {
        if ((this.mSplashView.views == null) || (this.mSplashView.views.isEmpty())) return;

        if ((alpha > 0.5F * this.topViews.size()/ this.mSplashView.views.size())
         && (this.topViews.size() < this.mSplashView.views.size()))
        {
            Log.d("JACK8","alphaAnimation2:"+alpha);
            addTopView(alpha);
        }

        for (TopView view : this.topViews) {
            view.doAnimation(alpha);
        }
    }



    private boolean inTopView(View view)
    {
        if (this.topViews.isEmpty()) {
            return false;
        }
        for (TopView topView : this.topViews) {
            if (topView.view == view) {
                return true;
            }
        }
        return false;
    }

    private void addTopView(float alpha)
    {
        int bound = this.mSplashView.views.size();
        int j=0;
        for(j=random.nextInt(bound); inTopView(this.mSplashView.views.get(j)); j = random.nextInt(bound));
        TopView topView = new TopView(this.mSplashView.views.get(j), alpha);
        this.topViews.add(topView);
    }



    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        if (interpolatedTime <=0.25F)
        {
            alphaAnimation1(mSplashView.text,interpolatedTime * 4.0F,textStart,textEnd);
        }
        if ((interpolatedTime > 0.125F) && (interpolatedTime <= 0.375F)) {
            alphaAnimation1(mSplashView.pic,4.0F * (interpolatedTime - 0.125F),picStart,picEnd);
        }
        if (interpolatedTime >0.375F)
        {
            scalaAnimation(1.6F * (interpolatedTime - 0.375F));
            alphaAnimation2(1.6F * (interpolatedTime - 0.375F));

        }
    }
}