package com.github.kongpf8848.animation.helper;

import androidx.annotation.Nullable;

import android.view.View;

public class ViewWrapper{

    private View view;
    public ViewWrapper(View view){
        this.view=view;
    }

    public int getWidth(){
        if(this.view!=null) {
            return view.getWidth();
        }
        return 0;
    }

    public void setWidth(int width){
        this.view.getLayoutParams().width=width;
       this.view.requestLayout();
    }
}
