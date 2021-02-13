package com.github.kongpf8848.animation.activity.tween.scale

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_cupid_shoot.*

class CupidActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_cupid_shoot
    }

    override fun enableStatusBar(): Boolean {
        return false
    }

    override fun customInitStatusBar() {
        initStatusBar(R.id.btn_rule)
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        img_cupid_shoot.setOnClickListener {
            doAnimation()
        }
    }

    private fun doAnimation() {

        cupid_result_text.setText(R.string.cupid_result_success)
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_jiayuan)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }
            override fun onAnimationEnd(animation: Animation) {
                cupid_result.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        cupid_result.visibility = View.VISIBLE
        cupid_result.startAnimation(animation)
    }
}