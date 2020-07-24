package com.github.kongpf8848.animation.widget;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.github.kongpf8848.animation.evaluator.ColorEvaluator;
import com.github.kongpf8848.animation.evaluator.PointFEvaluator;

/**
 * Created by pengf on 2017/3/23.
 */

public class MyAnimView extends View
{
    private  Paint mPaint;
    private PointF currentPoint;
    public static final float RADIUS = 50f;


    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        this.mPaint.setColor(Color.parseColor(color));
        invalidate();
    }


    public MyAnimView(Context context) {
        super(context);
    }

    public MyAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (currentPoint == null) {
            currentPoint = new PointF(getWidth()/2, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.x;
        float y = currentPoint.y;
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation()
    {
        PointF startPoint = new PointF(getWidth()/2, RADIUS);
        PointF endPoint = new PointF(getWidth()/2, getHeight() - RADIUS);
        final ValueAnimator anim = ValueAnimator.ofObject(new PointFEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("JACK8", "onAnimationUpdate1(),value="+animation.getAnimatedValue()+",fraction="+animation.getAnimatedFraction());
                currentPoint = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new BounceInterpolator());
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color",new ColorEvaluator(), "#0000FF","#FF0000");
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("JACK8", "onAnimationUpdate2(),value="+animation.getAnimatedValue()+",fraction="+animation.getAnimatedFraction());
            }
        });
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(anim2);
        animSet.setDuration(2000);
        animSet.start();

    }

}
