package com.github.kongpf8848.animation.activity.tween.scale

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Looper
import android.view.View
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_weizhi.*
import java.util.*

class WeizhiActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_weizhi
    }

    override fun fitsSystemWindows(): Boolean {
        return false
    }

    override fun statusBarColor(): Int {
        return R.color.transparent
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        val index = 1 + Random().nextInt(3)
        val resourceId = resources.getIdentifier("welcome_icon_$index", "mipmap", packageName)
        iv_activity_splash_pic.setImageResource(resourceId)
        iv_activity_splash_pic.alpha = 0.0f

        Looper.myQueue().addIdleHandler {
            startAnimation()
            false
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        iv_activity_splash_bg.clearAnimation()
        iv_activity_splash_pic.clearAnimation()
    }

    private fun startAnimation() {
        val animator1 = ObjectAnimator.ofFloat(iv_activity_splash_bg, View.ALPHA, 1.0f, 0.0f)
        val animator2 = ObjectAnimator.ofFloat(iv_activity_splash_pic, View.ALPHA, 0.0f, 1.0f)
        val animator3 = ObjectAnimator.ofPropertyValuesHolder(iv_activity_splash_pic, PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.1f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.1f))
        AnimatorSet().setDuration(1000L).apply {
            play(animator1).with(animator2).before(animator3)
            start()
        }
    }
}