package com.github.kongpf8848.animation.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import org.telegram.ui.Components.RLottieDrawable
import org.telegram.ui.Components.RLottieImageView

object EmojiUtils {

    private const val SUFFIX_TGS = ".tgs"
    private const val SUFFIX_JSON = ".json"

    //是否为tgs文件
    private fun isTgsFormat(str: String): Boolean {
        return !TextUtils.isEmpty(str) && str.endsWith(SUFFIX_TGS)
    }

    fun loadImageFromAsset(context: Context, fileName: String, imageView: RLottieImageView, width: Int, height: Int) {
        try {
            if (isTgsFormat(fileName)) {
                val drawable = RLottieDrawable(context, fileName, width, height, false, null)
                imageView.setAutoRepeat(true)
                imageView.setAnimation(drawable)
                imageView.playAnimation()
            } else {
                Glide.with(context).load("file:///android_asset/$fileName").into(imageView)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("JACK8", "error:" + e.message)
        }
    }
}