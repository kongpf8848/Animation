package com.github.kongpf8848.animation.activity.frame

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity

abstract class BaseFrameActivity : BaseActivity() {

    var imageView: ImageView? = null
    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        imageView=findViewById(R.id.anim_iv)
        Looper.myQueue().addIdleHandler {
            startAnim()
            false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        stopAnim()
    }

    private fun startAnim() {
        val drawable = imageView?.drawable
        if (drawable is Animatable) {
            drawable.start()
        }
    }

    private fun stopAnim() {
        val drawable = imageView?.drawable
        if (drawable is Animatable) {
            drawable.stop()
        }
    }
}