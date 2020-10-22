package com.github.kongpf8848.animation.bean;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import java.io.Serializable;

public class ImageBean implements Serializable {

    private int resId;
    private String name;

    public ImageBean(@DrawableRes int id, String name) {
        this.resId = id;
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int id) {
        this.resId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
