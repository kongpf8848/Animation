package com.github.kongpf8848.animation

import android.app.Application
import android.util.Log


class TKApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            e.printStackTrace()
            Log.d("Crash", "uncaughtException() called with: t = [" + t + "], e = [" + e.message + "]")
        }
        try {
            System.loadLibrary("tmessages.30")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        lateinit var instance: TKApplication
    }
}