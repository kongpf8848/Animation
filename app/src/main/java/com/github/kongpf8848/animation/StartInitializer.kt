package com.github.kongpf8848.animation

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.github.kongpf8848.animation.utils.LogUtils
import com.google.android.gms.ads.MobileAds


class StartInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LogUtils.d("JACK9", "StartInitializer create")
        MobileAds.initialize(context) {
            Log.d("JACK9", "initGoogleAds success called")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.d("JACK9", "StartInitializer dependencies()")
        return ArrayList<Class<out Initializer<*>>>().apply {
            add(StartA::class.java);
            add(StartB::class.java);
        }
    }
}