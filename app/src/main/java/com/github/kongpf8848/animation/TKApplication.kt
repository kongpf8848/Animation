package com.github.kongpf8848.animation

import android.app.Application
import com.github.kongpf8848.animation.utils.LogUtils
import org.telegram.messenger.AndroidUtilities

class TKApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        LogUtils.init(this, BuildConfig.DEBUG)
        AndroidUtilities.init(this)

        try {
            System.loadLibrary("tmessages.40")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        LogUtils.d("JACK8", "initGoogleAds end")
    }

    companion object {
        lateinit var instance: TKApplication
    }
}