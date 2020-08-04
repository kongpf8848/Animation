package com.github.kongpf8848.animation.activity.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface HookLayoutCallback {
    void onCreateViewCallback(View parent, String name, Context context, AttributeSet attrs, View destView);
}
