package com.github.kongpf8848.animation.activity.gif

import android.os.Bundle
import android.os.Looper
import android.support.rastermill.FrameSequenceDrawable
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.glide.GlideApp
import kotlinx.android.synthetic.main.activity_gif_demo.*

class GiflibActivity:BaseToolbarActivity(){

    override fun getLayoutId(): Int {
        return R.layout.activity_gif_demo
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        Looper.myQueue().addIdleHandler {
            loadImage()
            false
        }
    }

    private fun loadImage(){
        GlideApp.with(this).`as`(FrameSequenceDrawable::class.java)
                .load("http://2zhoumu-comic-public-test.oss-cn-hangzhou.aliyuncs.com/cover/comic/gc1100004.gif")
                .into(iv_gif)
    }
}