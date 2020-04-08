package com.github.kongpf8848.animation.helper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
