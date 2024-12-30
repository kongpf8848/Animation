package com.github.kongpf8848.animation.activity

import android.os.Bundle
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.fragment.WhatsNewFragment

class SparkSplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash_spark
    }

    override fun statusBarColor(): Int {
        return R.color.white
    }

    override fun statusBarDarkFont(): Boolean {
        return true
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        val ft=supportFragmentManager.beginTransaction()
        ft.replace(R.id.splash_form_container,WhatsNewFragment.newInstance(),WhatsNewFragment::class.java.name)
        ft.commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()
    }
}