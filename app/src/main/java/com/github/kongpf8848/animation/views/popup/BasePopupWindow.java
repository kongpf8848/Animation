/**
 * Secret
 * Copyright (C) 2019 Secret
 */
package com.github.kongpf8848.animation.views.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public abstract class BasePopupWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private int measuredWidth;
    private int measuredHeight;
    protected Context mContext;

    public abstract int getLayoutId();

    public BasePopupWindow(Context context) {
        this(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public BasePopupWindow(Context context, int width, int height){
        super(context);
        this.mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = inflater.inflate(getLayoutId(), null,false);
        updateMeasureInfo();
        this.setContentView(mPopView);
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setWidth(width);
        this.setHeight(height);
        init();
    }

    protected <T> T findViewById(int id){
        T view=null;
        if (mPopView != null) {
            view=(T)mPopView.findViewById(id);
        }
        return view;
    }

    protected void init() {

    }

    protected void updateMeasureInfo(){
        mPopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        measuredWidth=mPopView.getMeasuredWidth();
        measuredHeight=mPopView.getMeasuredHeight();
    }

    public int getMeasuredWidth() {
        return measuredWidth;
    }

    public int getMeasuredHeight() {
        return measuredHeight;
    }

    @Override
    public void onClick(View v) {

    }
}
