package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.frame.*

class FrameAnimActivity : BaseToolbarActivity() {

    public override fun getLayoutId(): Int {
        return R.layout.activity_anim_frame
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(Frame_1_Activity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(Frame_2_Activity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(Frame_3_Activity::class.java)
    }

    @OnClick(R.id.button4)
    fun onButton4(view: View?) {
        startActivity(Frame_4_Activity::class.java)
    }

    @OnClick(R.id.button5)
    fun onButton5(view: View?) {
        startActivity(Frame_5_Activity::class.java)
    }

    @OnClick(R.id.button6)
    fun onButton6(view: View?) {
        startActivity(Frame_6_Activity::class.java)
    }

    @OnClick(R.id.button7)
    fun onButton7(view: View?) {
        startActivity(Frame_7_Activity::class.java)
    }

    @OnClick(R.id.button8)
    fun onButton8(view: View?) {
        startActivity(Frame_8_Activity::class.java)
    }

    @OnClick(R.id.button9)
    fun onButton9(view: View?) {
        startActivity(Frame_9_Activity::class.java)
    }
}