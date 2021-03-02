package com.github.kongpf8848.animation.activity.tween

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.activity.tween.scale.CupidActivity
import com.github.kongpf8848.animation.activity.tween.scale.QimangxingActivity
import com.github.kongpf8848.animation.activity.tween.scale.WeizhiActivity

class TweenScaleActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tween_scale
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(CupidActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(WeizhiActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(QimangxingActivity::class.java)
    }
}