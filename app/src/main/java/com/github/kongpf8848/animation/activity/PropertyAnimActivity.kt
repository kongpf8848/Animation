package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.property.BallActivity
import com.github.kongpf8848.animation.activity.property.PingoLoginActivity
import com.github.kongpf8848.animation.activity.property.PropertyDemoActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

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

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(PingoLoginActivity::class.java)
    }

}