package com.github.kongpf8848.animation.activity.tween.alpha

import android.animation.*
import android.os.Bundle
import android.view.View
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_user_guide_cartoon.*

class TranslateActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_user_guide_cartoon
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        startAnimation()
    }

    private fun startAnimation() {
        val animation1 = ObjectAnimator.ofFloat(augc_img_women, View.ALPHA, 0f, 1f)
        animation1.duration = 2000
        animation1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                augc_img_women.visibility = View.VISIBLE
            }
        })
        augc_img_hello_chinese.pivotX = 0f
        augc_img_hello_chinese.pivotY = 0f
        val holderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f, 1.0f)
        val holderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f, 1.0f)
        val animation2 = ObjectAnimator.ofPropertyValuesHolder(augc_img_hello_chinese, holderX, holderY)
        animation2.duration = 1000
        animation2.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                augc_img_hello_chinese.visibility = View.VISIBLE
            }
        })
        val animation3 = ObjectAnimator.ofFloat(augc_img_men, View.ALPHA, 0f, 1f)
        animation3.duration = 2000
        animation3.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                augc_img_men.visibility = View.VISIBLE
            }
        })
        val animation4 = ObjectAnimator.ofPropertyValuesHolder(augc_img_hello_english, holderX, holderY)
        animation4.duration = 1000
        animation4.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                augc_img_hello_english.visibility = View.VISIBLE
            }
        })
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(animation1, animation2, animation3, animation4)
        animatorSet.start()
    }
}