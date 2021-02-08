package com.github.kongpf8848.animation.activity.lottie

import com.github.kongpf8848.animation.R

class AutoHomeActivity : LottieTabActivity() {

    override fun getTabs() = mapOf(
            "首页" to "tab_article.json",
            "论坛" to "tab_club.json",
            "选车" to "tab_car.json",
            "新车特卖" to "tab_used_car.json",
            "我" to "tab_me.json"
    )

    override fun getTabLayoutId()=R.layout.item_tab_autohome


}