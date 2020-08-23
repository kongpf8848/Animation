package com.github.kongpf8848.animation.splash;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.viewpager.HookLayoutCallback;
import com.github.kongpf8848.animation.activity.viewpager.HookLayoutInflaterFactory;
import com.github.kongpf8848.animation.activity.viewpager.ParallaxViewImp;
import com.github.kongpf8848.animation.activity.viewpager.ParallaxViewTag;


public class SplashLayoutInflater extends LayoutInflater implements HookLayoutCallback {

    private ParallaxViewImp mParallaxView;

    public SplashLayoutInflater(LayoutInflater original, Context newContext, ParallaxViewImp parallaxView) {
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
        Log.d("SplashLayoutInflater", "onCreateViewCallback() called with: view = [" + view + "], name = [" + name + "], context = [" + context + "], attrs = [" + attrs + "]");

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SplashView);
        int count = a.getIndexCount();
        if (count > 0) {
            boolean bSplash = a.getBoolean(R.styleable.SplashView_splash, false);
            if (mParallaxView != null) {
                Log.d("SplashLayoutInflater", "addview=view="+view);
                mParallaxView.getParallaxViews().add(view);
            }
            a.recycle();
        }

    }
}