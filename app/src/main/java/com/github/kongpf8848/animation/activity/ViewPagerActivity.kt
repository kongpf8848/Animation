package com.github.kongpf8848.animation.activity

import android.view.View
import butterknife.OnClick
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.viewpager.WeChatActivity
import com.github.kongpf8848.animation.base.BaseToolbarActivity

class ViewPagerActivity : BaseToolbarActivity() {

    public override fun getLayoutId(): Int {
        return R.layout.activity_viewpager
    }

    @OnClick(R.id.button1)
    fun onButton1(view: View?) {
        startActivity(WeChatActivity::class.java)
    }

    @OnClick(R.id.button2)
    fun onButton2(view: View?) {
        startActivity(SparkSplashActivity::class.java)
    }

}