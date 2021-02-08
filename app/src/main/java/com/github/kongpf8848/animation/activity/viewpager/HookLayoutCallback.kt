package com.github.kongpf8848.animation.activity.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.View

interface HookLayoutCallback {
    fun onCreateViewCallback(parent: View?, name: String?, context: Context?, attrs: AttributeSet?, destView: View?)
}