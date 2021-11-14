package com.github.kongpf8848.animation

import android.app.Application
import android.util.Log
import com.github.kongpf8848.animation.utils.LogUtils
import org.telegram.messenger.AndroidUtilities

class TKApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
//        Thread.setDefaultUncaughtExceptionHandler { t, e ->
//            e.printStackTrace()
//            Log.d("Crash", "uncaughtException() called with: t = [" + t + "], e = [" + e.message + "]")
//        }
//        try {
//            System.loadLibrary("tmessages.40")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        LogUtils.init(this,BuildConfig.DEBUG)
        AndroidUtilities.init(this)
    }

    companion object {
        lateinit var instance: TKApplication
    }
}