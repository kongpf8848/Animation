package com.github.kongpf8848.animation.activity.lottie

import com.github.kongpf8848.animation.R

class BossActivity : LottieTabActivity() {

    override fun getTabs() = mapOf(
            "职位" to "lottie_position.json",
            "发现" to "lottie_discover.json",
            "消息" to "lottie_message.json",
            "我的" to "lottie_me.json"
    )

    override fun getTabLayoutId()= R.layout.item_tab_boss

}