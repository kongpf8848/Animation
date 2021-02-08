package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.transition.ShareElementOneActivity

class TransitionActivity : BaseToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_transition
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(ShareElementOneActivity::class.java)
    }
}