package com.github.kongpf8848.animation.activity.lottie

import com.github.kongpf8848.animation.R

class CardActivity : LottieTabActivity(){

    override fun getTabs()= mapOf(
            "打卡" to "lottie_card_3.json",
            "契约" to "lottie_contract_3.json",
            "消息" to "lottie_message_3.json",
            "我的" to "lottie_my_3.json"
    )

    override fun getTabLayoutId()= R.layout.item_tab_card

}