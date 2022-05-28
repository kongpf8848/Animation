package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.telegram.IntroActivity
import com.github.kongpf8848.animation.activity.telegram.TgsActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class TelegramActivity : BaseToolbarActivity()  {
    override fun getLayoutId(): Int {
        return R.layout.activity_telegram
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(IntroActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(TgsActivity::class.java)
    }
}