package com.github.kongpf8848.animation.activity.lottie

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_lottie_pdj_guide.*
import kotlinx.android.synthetic.main.activity_lottie_spark.*

open class SparkActivity: BaseActivity() {
    var flag=false
    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_spark
    }

    override fun statusBarColor(): Int {
        return R.color.spark_blue
    }

    override fun navigationBarColor(): Int {
        return R.color.spark_blue
    }


    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        login_form_circle_lottie.addAnimatorUpdateListener(object: AnimatorUpdateListener{

            override fun onAnimationUpdate(animation: ValueAnimator) {
                Log.d(TAG, "onAnimationUpdate() called with: animation = ${animation.animatedFraction},${animation.animatedValue}")
                if(animation.animatedFraction>=0.5f && !flag){
                    flag=true
                    motion_layout.transitionToEnd()
                }
            }
        })
        login_form_circle_lottie.playAnimation()
        login_form_logo_lottie.playAnimation();
    }
}