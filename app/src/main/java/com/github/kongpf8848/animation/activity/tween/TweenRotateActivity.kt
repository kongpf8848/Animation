package com.github.kongpf8848.animation.activity.tween

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.activity.tween.rotate.Rotate_8_Activity

class TweenRotateActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tween_rotate
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?)
    {
        startActivity(Rotate_8_Activity::class.java)
    }
}