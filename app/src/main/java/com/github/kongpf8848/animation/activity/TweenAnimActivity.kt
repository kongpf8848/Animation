package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.tween.TweenAlphaActivity
import com.github.kongpf8848.animation.activity.tween.TweenRotateActivity
import com.github.kongpf8848.animation.activity.tween.TweenScaleActivity
import com.github.kongpf8848.animation.activity.tween.TweenTranslateActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class TweenAnimActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_anim_tween
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(TweenAlphaActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(TweenScaleActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(TweenTranslateActivity::class.java)
    }

    @OnClick(R.id.button4)
    fun onButton4(view: View?) {
        startActivity(TweenRotateActivity::class.java)
    }
}