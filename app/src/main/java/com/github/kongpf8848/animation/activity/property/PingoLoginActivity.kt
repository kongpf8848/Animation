package com.github.kongpf8848.animation.activity.property

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity

class PingoLoginActivity : BaseActivity() {

    private var objectAnimator1: ObjectAnimator? = null
    private var objectAnimator2: ObjectAnimator? = null
    private var objectAnimator3: ObjectAnimator? = null
    lateinit var bg_one_image:View
    lateinit var bg_two_image:View
    lateinit var bg_three_image:View


    private var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                bg_two_image.visibility = View.VISIBLE
                objectAnimator2?.start()
                sendEmptyMessageDelayed(2, 3600L)
                removeMessages(1)
            } else if (msg.what == 2) {
                bg_three_image.visibility = View.VISIBLE
                objectAnimator3?.start()
                removeMessages(2)
            }
        }
    }

    override fun statusBarColor(): Int {
        return R.color.transparent
    }

    override fun navigationBarColor(): Int {
        return R.color.transparent
    }

    override fun fitsSystemWindows(): Boolean {
        return false
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_property_login
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        bg_one_image=findViewById(R.id.bg_one_image)
        bg_two_image=findViewById(R.id.bg_two_image)
        bg_three_image=findViewById(R.id.bg_three_image)

        val alpha1 = PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f)
        val alpha2 = PropertyValuesHolder.ofFloat(View.ALPHA, 0.1f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f)
        val alpha3 = PropertyValuesHolder.ofFloat(View.ALPHA, 0.1f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f)
        val scaleX1 = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f, 1.0f)
        val scaleY1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f, 1.0f)
        val scaleX2 = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.2f)
        val scaleY2 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.2f)

        objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(bg_one_image, alpha1, scaleX1, scaleY1).setDuration(3500)
        objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(bg_two_image, alpha2, scaleX2, scaleY2).setDuration(4000)
        objectAnimator3 = ObjectAnimator.ofPropertyValuesHolder(bg_three_image, alpha3, scaleX1, scaleY1).setDuration(3000)
        objectAnimator3?.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                startAnimation()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        objectAnimator1?.cancel()
        objectAnimator2?.cancel()
        objectAnimator3?.cancel()
        handler.removeMessages(1)
        handler.removeMessages(2)
    }

    override fun onResume() {
        super.onResume()
        startAnimation()
    }

    private fun startAnimation() {
        bg_two_image.visibility = View.GONE
        bg_three_image.visibility = View.GONE
        objectAnimator1?.start()
        this.handler.sendEmptyMessageDelayed(1, 3150L)
    }
}