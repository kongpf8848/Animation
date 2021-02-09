package com.github.kongpf8848.animation.activity.tween.alpha

import android.view.KeyEvent
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity

class OtherActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_other
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            finish()
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}