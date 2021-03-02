package com.github.kongpf8848.animation.activity.tween.rotate

import android.os.Bundle
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rotate_music.*

class MusicActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_rotate_music
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        Looper.myQueue().addIdleHandler {
            startAnimation()
            false
        }
    }

    private fun startAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate).apply {
            duration = 36000
            repeatCount=Animation.INFINITE
            interpolator = LinearInterpolator()
        }
        song_image.startAnimation(animation)
    }
}