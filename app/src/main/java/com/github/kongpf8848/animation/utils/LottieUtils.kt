package com.github.kongpf8848.animation.utils

import android.content.Context
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable

object LottieUtils{

    fun loadAssetFile(context: Context, lottieImageView:LottieAnimationView, fileName:String,repeatCount:Int= LottieDrawable.INFINITE,autoPlay:Boolean=true){
        val lottieCompose= if(fileName.endsWith(".zip")){
            LottieCompositionFactory.fromAssetSync(context, fileName).value
         }
        else{
            LottieCompositionFactory.fromAssetSync(context, fileName.plus(".zip")).value
         }
        lottieImageView.progress=0.0f
        lottieImageView.repeatCount=repeatCount
        lottieImageView.setComposition(lottieCompose!!)
        if(autoPlay){
            lottieImageView.playAnimation()
        }

    }

}