package com.github.kongpf8848.animation.activity.gif

import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class GlideActivity : BaseToolbarActivity() {
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
        Glide.with(this).load(R.raw.zixian).into(iv_gif)
    }
}