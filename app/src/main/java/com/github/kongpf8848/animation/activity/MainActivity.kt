package com.github.kongpf8848.animation.activity

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_toolbar.*

class MainActivity : BaseToolbarActivity() {

    override fun enableStatusBar(): Boolean {
        return false
    }

    public override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar?.navigationIcon=null
    }


    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(FrameAnimActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(TweenAnimActivity::class.java)
    }

    @OnClick(R.id.button3)
    fun onButton3(view: View?) {
        startActivity(PropertyAnimActivity::class.java)
    }

    @OnClick(R.id.button4)
    fun onButton4(view: View?) {
        startActivity(LottieAnimActivity::class.java)
    }

    @OnClick(R.id.button5)
    fun onButton5(view: View?) {
        startActivity(TgsActivity::class.java)
    }

    @OnClick(R.id.button6)
    fun onButton6(view: View?) {
        startActivity(ViewPagerActivity::class.java)
    }

    @OnClick(R.id.button7)
    fun onButton7(view: View?) {
        startActivity(TransitionActivity::class.java)
    }

    @OnClick(R.id.button8)
    fun onButton8(view: View?) {
        startActivity(GifActivity::class.java)
    }

    @OnClick(R.id.button9)
    fun onButton9(view: View?) {
        startActivity(SVGAActivity::class.java)
    }
}