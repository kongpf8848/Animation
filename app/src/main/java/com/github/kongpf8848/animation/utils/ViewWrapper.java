package com.github.kongpf8848.animation.utils;

import android.view.View;

/**
 * View包装类,方便使用ObjectAnimator对View宽度/高度做属性动画
 */
public class ViewWrapper {

    private View view;
    public ViewWrapper(View view){
        this.view=view;
    }

    public void setWidth(int width){
        if(this.view!=null) {

            this.view.getLayoutParams().width = width;
            this.view.requestLayout();
        }
    }

    public int getWidth(){
        if(this.view!=null) {
            return view.getWidth();
        }
        return 0;
    }

    public void setHeight(int height){
        if(this.view!=null) {
            this.view.getLayoutParams().height = height;
            this.view.requestLayout();
        }
    }

    public int getHeight(){
        if(this.view!=null) {
            return view.getHeight();
        }
        return 0;
    }

}
