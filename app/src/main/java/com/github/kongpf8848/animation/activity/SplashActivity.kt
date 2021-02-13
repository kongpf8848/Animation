package com.github.kongpf8848.animation.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.github.kongpf8848.animation.R
import com.kongpf.commonhelper.ApkHelper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun statusBarColor(): Int {
        return R.color.colorPrimary
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        tv_version.text="V${ApkHelper.getAppVersionName(this)}"
        
        anim_logo.apply {
            addOffsetAnimListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    Log.d("AnimLogoView", "Offset anim end")
                }
            })
            addGradientAnimListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    Log.d("AnimLogoView", "Gradient anim end")
                    startActivity(GuideActivity::class.java, true)
                }
            })
        }
        Looper.myQueue().addIdleHandler {
            anim_logo.startAnimation()
            false
        }
    }
}