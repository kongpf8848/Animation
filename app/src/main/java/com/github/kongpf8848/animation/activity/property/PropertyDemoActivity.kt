package com.github.kongpf8848.animation.activity.property

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_property_demo.*

class PropertyDemoActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_property_demo
    }

    @OnClick(R.id.button1)
    fun onButton1() {
        ObjectAnimator.ofFloat(iv_boy, View.ALPHA, 1f, 0f, 1f).apply {
            repeatMode = ValueAnimator.REVERSE
            duration = 1000
        }.start()
    }

    @OnClick(R.id.button2)
    fun onButton2() {
        ObjectAnimator.ofPropertyValuesHolder(iv_boy,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f, 1f)
        ).setDuration(1000).start()
    }

    @OnClick(R.id.button3)
    fun onButton3() {
        ObjectAnimator.ofFloat(iv_boy, View.TRANSLATION_X, -200f, 200f, 0f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    @OnClick(R.id.button4)
    fun onButton4() {
        ObjectAnimator.ofFloat(iv_boy, View.ROTATION, 0f, 360f).apply {
            duration = 1000
        }.start()
    }

    @OnClick(R.id.button5)
    fun onButton5() {
        ObjectAnimator.ofFloat(iv_boy, "aa", 1.0f, 0.0f, 1.0f).apply {
            duration = 1000
        }.start()
    }

    @OnClick(R.id.button6)
    fun onButton6() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        animator.setTarget(iv_boy)
        animator.start()
    }

    @OnClick(R.id.button7)
    fun onButton7() {
        iv_boy.animate().scaleX(1.1f).scaleY(1.1f).setDuration(1000).start()
    }
}