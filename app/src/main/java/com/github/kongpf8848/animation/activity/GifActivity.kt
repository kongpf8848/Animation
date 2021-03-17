package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.gif.GlideActivity
import com.github.kongpf8848.animation.activity.lottie.AutoHomeActivity
import com.github.kongpf8848.animation.activity.lottie.BossActivity
import com.github.kongpf8848.animation.activity.lottie.CardActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class GifActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_gif
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
       startActivity(GlideActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {

    }

}