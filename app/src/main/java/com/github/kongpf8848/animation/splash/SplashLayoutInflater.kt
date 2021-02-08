package com.github.kongpf8848.animation.splash

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.viewpager.ParallaxViewImp
import com.github.kongpf8848.animation.hook.HookLayoutCallback
import com.github.kongpf8848.animation.hook.HookLayoutInflaterFactory


class SplashLayoutInflater(original: LayoutInflater?, newContext: Context?, private val mParallaxView: ParallaxViewImp?)
    : LayoutInflater(original, newContext), HookLayoutCallback {

    override fun cloneInContext(newContext: Context): LayoutInflater {
        return from(newContext)
    }

    override fun onCreateViewCallback(parent: View?, name: String, context: Context, attrs: AttributeSet, view: View) {
        Log.d("SplashLayoutInflater", "onCreateViewCallback() called with: view = [$view], name = [$name], context = [$context], attrs = [$attrs]")
        val ta = context!!.obtainStyledAttributes(attrs, R.styleable.SplashView)
        val count = ta.indexCount
        if (count > 0) {
            val bSplash = ta.getBoolean(R.styleable.SplashView_splash, false)
            if (mParallaxView != null && bSplash) {
                Log.d("SplashLayoutInflater", "addview=view=$view")
                mParallaxView.getParallaxViews()!!.add(view)
            }
            ta.recycle()
        }
    }

    init {
        factory2 = HookLayoutInflaterFactory((newContext as Activity?)!!, this, this)
    }
}