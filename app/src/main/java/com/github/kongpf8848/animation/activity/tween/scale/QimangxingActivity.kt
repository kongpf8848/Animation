package com.github.kongpf8848.animation.activity.tween.scale

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_qimangxing.*

class QimangxingActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_qimangxing
    }

    override fun fitsSystemWindows(): Boolean {
        return false
    }

    override fun statusBarColor(): Int {
        return R.color.transparent
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        val scaleAnimation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 2000L
        scaleAnimation.fillAfter = true
        anim_iv.startAnimation(scaleAnimation)
    }
}