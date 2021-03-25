package com.github.kongpf8848.animation.bean

import java.io.Serializable

data class LottieConfig(
        val radius: Int,
        val totalPageNum: Int,
        val lottieBgName: String,
        val pageAnimTime: Long,
        val buttonAnimTime: Long,
        val lottieTitle:List<LottieData>,
        val lottieMain:List<LottieData>
):Serializable
data class LottieData(val lottieName:String, val repeatInterval:RepeatInterval?=null):Serializable
data class RepeatInterval(val start:Float,val end:Float):Serializable