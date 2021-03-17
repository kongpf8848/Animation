package com.github.kongpf8848.animation.activity.gif

import android.os.Bundle
import android.os.Looper
import com.bumptech.glide.Glide
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_gif_glide.*

class GlideActivity:BaseToolbarActivity(){

    override fun getLayoutId(): Int {
        return R.layout.activity_gif_glide
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        Looper.myQueue().addIdleHandler {
            loadImage()
            false
        }
    }

    private fun loadImage(){
        Glide.with(this).load(R.raw.zixian).into(iv_gif)
    }
}