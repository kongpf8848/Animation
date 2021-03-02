package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.lottie.AutoHomeActivity
import com.github.kongpf8848.animation.activity.lottie.BossActivity
import com.github.kongpf8848.animation.activity.lottie.CardActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class LottieAnimActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(AutoHomeActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(BossActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(CardActivity::class.java)
    }
}