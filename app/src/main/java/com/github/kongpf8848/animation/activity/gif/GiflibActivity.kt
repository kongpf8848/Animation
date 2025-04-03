package com.github.kongpf8848.animation.activity.gif

import android.os.Bundle
import android.os.Looper
import android.support.rastermill.FrameSequenceDrawable
import android.widget.ImageView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.glide.GlideApp


class GiflibActivity : BaseToolbarActivity() {

    lateinit var iv_gif: ImageView

    override fun getLayoutId(): Int {
        return R.layout.activity_gif_demo
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        iv_gif = findViewById(R.id.iv_gif)
        Looper.myQueue().addIdleHandler {
            loadImage()
            false
        }
    }

    private fun loadImage() {
        GlideApp.with(this).asGif2()
            .load(R.raw.test)
            .into(iv_gif)
    }
}