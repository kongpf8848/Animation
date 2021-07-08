package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.gif.GlideActivity
import com.github.kongpf8848.animation.activity.svga.ZiroomActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class SVGAActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_svga
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(ZiroomActivity::class.java)
    }


}