package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.property.BallActivity
import com.github.kongpf8848.animation.activity.property.PropertyDemoActivity

class PropertyAnimActivity : BaseToolbarActivity() {

    public override fun getLayoutId(): Int {
        return R.layout.activity_anim_property
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(PropertyDemoActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(BallActivity::class.java)
    }

}