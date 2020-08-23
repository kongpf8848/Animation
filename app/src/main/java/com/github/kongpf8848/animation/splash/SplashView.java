package com.github.kongpf8848.animation.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.kongpf8848.animation.R;

import java.util.ArrayList;
import java.util.List;

public class SplashView extends RelativeLayout{

    public View text;
    public View pic;
    public View certificate;
    public List<View> views;
    private SplashAnimation animation;

    public SplashView(Context context) {
        this(context,null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        pic = findViewById(R.id.pic);
        text = findViewById(R.id.text);
        certificate = findViewById(R.id.certificate);

        views=new ArrayList<>();

    }

    public void setViews(List<View> views) {
        this.views=views;
    }


    public void startAnimation()
    {
        animation=new SplashAnimation(this);
        animation.setDuration(2000L);
        startAnimation(animation);
    }

    public void resetAnimation(){
        if (pic != null) {
            pic.setAlpha(0.0F);
        }
        if (text != null) {
            text.setAlpha(0.0F);
        }
        if (certificate != null) {
            certificate.setAlpha(0.0F);
        }
        for(View view:views){
            view.setAlpha(0.0F);
        }
        if(animation!=null){
            animation.cancel();
        }
    }


}
