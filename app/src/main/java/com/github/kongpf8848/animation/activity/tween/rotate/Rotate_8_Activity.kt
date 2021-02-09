package com.github.kongpf8848.animation.activity.tween.rotate

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import butterknife.BindView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_rotate_8.*

class Rotate_8_Activity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_rotate_8
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        startAnimation()
    }

    private fun startAnimation() {
        ObjectAnimator.ofFloat(iv_loading, View.ROTATION_Y, 180f).apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount=ValueAnimator.INFINITE
            interpolator = AccelerateInterpolator()
            duration = 1000
        }.start()
    }
}