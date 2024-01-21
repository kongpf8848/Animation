package com.github.kongpf8848.animation

import android.app.Application
import com.github.kongpf8848.animation.utils.LogUtils
import com.google.android.gms.ads.MobileAds
import org.telegram.messenger.AndroidUtilities

class TKApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        try {
            System.loadLibrary("tmessages.40")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        LogUtils.init(this,BuildConfig.DEBUG)
        AndroidUtilities.init(this)
        MobileAds.initialize(this) {
            LogUtils.d("JACK8", "initGoogleAds success called")
        }
        LogUtils.d("JACK8", "initGoogleAds end")
    }

    companion object {
        lateinit var instance: TKApplication
    }
}