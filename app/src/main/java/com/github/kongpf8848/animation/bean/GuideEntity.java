package com.github.kongpf8848.animation.bean;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.github.kongpf8848.animation.BaseApplication;
import com.kongpf.commonhelper.ScreenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by pengf on 2016/12/15.
 */

public class GuideEntity
{
    public static Random random = new Random();
    public static int len = ScreenHelper.dp2px(BaseApplication.applicationContext, 10.0F);

    public static void a(GuideItem guideItem)
    {
        if(guideItem==null || guideItem.viewGroup==null)return;
        guideItem.flag=false;
        MyAnimation myAnimation=new MyAnimation(guideItem);
        myAnimation.setDuration(2000L);
        myAnimation.setInterpolator(new LinearInterpolator());
        guideItem.viewGroup.startAnimation(myAnimation);

    }

    public static void b(GuideItem guideItem)
    {
        if(guideItem==null || guideItem.viewGroup==null)return;
        guideItem.flag=true;

        if (guideItem.pic != null) {
            guideItem.pic.setAlpha(0.0F);
        }
        if (guideItem.text != null) {
            guideItem.text.setAlpha(0.0F);
        }
        if (guideItem.certificate != null) {
            guideItem.certificate.setAlpha(0.0F);
        }
        Iterator iterator = guideItem.views.iterator();
        while (iterator.hasNext())
        {
            ((View)iterator.next()).setAlpha(0.0F);
        }
    }

    private static class a
    {
        final float a = 0.5F;
        public View b;
        public float c;
        public int d;
        FrameLayout.LayoutParams e;

        public a(View paramView, float paramFloat)
        {
            this.b = paramView;
            this.c = paramFloat;
            this.e = ((FrameLayout.LayoutParams)paramView.getLayoutParams());
            this.d = this.e.topMargin;
        }

        public void a(float paramFloat)
        {
            float f1 = 0.5F;
            float f2 = paramFloat - this.c;
            if (f2 > f1)
            {
                f2=f1;
            }

            float f3 = f2 * 2.0F;
            this.b.setAlpha(f3);
            this.e.topMargin = ((int)((1.0F - f3) * len + this.d));
            this.b.setLayoutParams(this.e);

        }
    }


    public static class GuideItem
    {
        public boolean flag = false;
        public ViewGroup viewGroup;
        public View text;
        public View pic;
        public View certificate;
        public List<View> views;
    }

    private static class MyAnimation extends Animation {

        private GuideItem guideItem;

        private int b= ScreenHelper.getScreenWidth(BaseApplication.applicationContext);
        private int f=0;
        private int e=0;
        private int c=0;
        private int d=0;

        public ArrayList<GuideEntity.a> a = new ArrayList<GuideEntity.a>();

        public MyAnimation(GuideItem guideItem)
        {
            this.guideItem=guideItem;
            int i = this.guideItem.text.getMeasuredWidth();
            this.e = (-(i / 2));
            this.f = (this.b - i) / 2;

            int j = this.guideItem.pic.getMeasuredWidth();
            this.c = -(j / 2);
            this.d = (this.b - j) / 2;
        }

        private void a(float alpha)
        {
            if(guideItem.text==null)return;
            this.guideItem.text.setAlpha(alpha);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.guideItem.text.getLayoutParams();
            if (layoutParams == null)
            {
                layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.rightMargin = ((int)(this.e + alpha * (this.f - this.e)));
            this.guideItem.text.setLayoutParams(layoutParams);
        }

        private void b(float alpha)
        {
            if (this.guideItem == null) {
                return;
            }
            this.guideItem.pic.setAlpha(alpha);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.guideItem.pic.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.rightMargin = ((int)(this.c + alpha * (this.d - this.c)));
            this.guideItem.pic.setLayoutParams(layoutParams);
        }

        private void c(float alpha)
        {
            if ((this.guideItem == null) || (this.guideItem.certificate == null)) return;
            this.guideItem.certificate.setAlpha(alpha);
            float f1 = 2.0F - alpha;
            this.guideItem.certificate.setScaleX(f1);
            this.guideItem.certificate.setScaleY(f1);
        }

        private boolean a(View paramView)
        {
            if (this.a.isEmpty()) {
                return false;
            }
            Iterator localIterator = this.a.iterator();
            while (localIterator.hasNext())
            {
                if (((GuideEntity.a) localIterator.next()).b == paramView)
                {
                    return true;
                }
            }
            return false;
        }

        private GuideEntity.a d(float alpha)
        {
            int i = this.guideItem.views.size();
            int j=0;
            for (j=random.nextInt(i); a(this.guideItem.views.get(j)); j = random.nextInt(i));
            GuideEntity.a locala = new GuideEntity.a((View)this.guideItem.views.get(j), alpha);
            this.a.add(locala);
            return locala;
        }

        private void e(float alpha)
        {
            if ((this.guideItem.views == null) || (this.guideItem.views.isEmpty())) return;

            if ((alpha > 1.0F / this.guideItem.views.size() / 2.0F * this.a.size()) && (this.a.size() < this.guideItem.views.size()))
            {
                d(alpha);
            }
            Iterator iterator = this.a.iterator();
            while (iterator.hasNext())
            {
                ((GuideEntity.a) iterator.next()).a(alpha);
            }
        }



        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            if(guideItem.flag)return;
            if (interpolatedTime < 0.25F)
            {
                a(interpolatedTime * 4.0F);
            }
            if ((interpolatedTime > 0.125F) && (interpolatedTime < 0.375F)) {
                b(4.0F * (interpolatedTime - 0.125F));
            }
            if (interpolatedTime > 0.375F)
            {
                e(1.6F * (interpolatedTime - 0.375F));
                c(1.6F * (interpolatedTime - 0.375F));
            }
        }
    }
}
