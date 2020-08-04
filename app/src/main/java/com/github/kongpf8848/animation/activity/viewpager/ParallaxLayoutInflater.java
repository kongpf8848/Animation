package com.github.kongpf8848.animation.activity.viewpager;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.kongpf8848.animation.R;


public class ParallaxLayoutInflater extends LayoutInflater implements HookLayoutCallback {

    private ParallaxViewImp mParallaxView;

    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxViewImp parallaxView) {
        super(original, newContext);
        this.mParallaxView = parallaxView;
        setFactory2(new HookLayoutInflaterFactory((Activity) newContext, this, this));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return LayoutInflater.from(newContext);
    }

    @Override
    public void onCreateViewCallback(View parent, String name, Context context, AttributeSet attrs,View view) {
        Log.d("ParallaxLayoutInflater", "onCreateViewCallback() called with: view = [" + view + "], name = [" + name + "], context = [" + context + "], attrs = [" + attrs + "]");

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimationView);
        int count = a.getIndexCount();
        if (count > 0) {
            ParallaxViewTag tag = new ParallaxViewTag();
            tag.xIn = a.getFloat(R.styleable.AnimationView_x_in, 0f);
            tag.xOut = a.getFloat(R.styleable.AnimationView_x_out, 0f);
            tag.yIn = a.getFloat(R.styleable.AnimationView_y_in, 0f);
            tag.yOut = a.getFloat(R.styleable.AnimationView_y_in, 0f);

            view.setTag(R.id.animation_id, tag);

            if (mParallaxView != null) {
                mParallaxView.getParallaxViews().add(view);
            }
            a.recycle();
        }

    }
}