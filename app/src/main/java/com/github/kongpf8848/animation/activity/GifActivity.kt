package com.github.kongpf8848.animation.activity

import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.gif.FrameSequenceActivity
import com.github.kongpf8848.animation.activity.gif.GiflibActivity
import com.github.kongpf8848.animation.activity.gif.GlideActivity
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
        startActivity(GiflibActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        val intent = Intent(this, FrameSequenceActivity::class.java)
        intent.putExtra("resourceId", R.raw.animated_gif)
        startActivity(intent)
    }

}