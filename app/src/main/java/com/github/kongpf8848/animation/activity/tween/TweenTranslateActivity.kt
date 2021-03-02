package com.github.kongpf8848.animation.activity.tween

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.activity.tween.translate.FidoActivity
import com.github.kongpf8848.animation.activity.tween.translate.HeartActivity
import com.github.kongpf8848.animation.activity.tween.translate.ShakeActivity

class TweenTranslateActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tween_translate
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(ShakeActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(HeartActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(FidoActivity::class.java)
    }
}