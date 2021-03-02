package com.github.kongpf8848.animation.activity.tween

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.activity.tween.rotate.MusicActivity
import com.github.kongpf8848.animation.dialog.RingProgressDialog
import com.github.kongpf8848.animation.dialog.ShareDialog

class TweenRotateActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tween_rotate
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?)
    {
        startActivity(MusicActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?)
    {
        RingProgressDialog(this).show("RingProgressDialog")
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?)
    {
        ShareDialog(this).show("ShareDialog")
    }
}