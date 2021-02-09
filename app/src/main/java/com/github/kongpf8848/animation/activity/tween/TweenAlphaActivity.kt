package com.github.kongpf8848.animation.activity.tween

import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.activity.tween.alpha.OtherActivity
import com.github.kongpf8848.animation.activity.tween.alpha.TranslateActivity

class TweenAlphaActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tween_alpha
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(OtherActivity::class.java)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(OtherActivity::class.java)
        overridePendingTransition(R.anim.my_scale_action, R.anim.fade_out)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(OtherActivity::class.java)
        overridePendingTransition(R.anim.scale_rotate, R.anim.fade_out)
    }

    @OnClick(R.id.button4)
    fun onButton4(view: View?) {
        startActivity(OtherActivity::class.java)
        overridePendingTransition(R.anim.scale_translate, R.anim.fade_out)
    }

    @OnClick(R.id.button5)
    fun onButton5(view: View?) {
        startActivity(OtherActivity::class.java)
        overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out)
    }

    @OnClick(R.id.button6)
    fun onButton6(view: View?) {
        startActivity(TranslateActivity::class.java)
    }
}