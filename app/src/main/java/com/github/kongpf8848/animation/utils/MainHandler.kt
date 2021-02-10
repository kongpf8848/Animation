package com.github.kongpf8848.animation.utils

import android.os.Handler
import android.os.Looper

class MainHandler : Handler(Looper.getMainLooper()) {
    private object MainHandler {
        val holder = MainHandler()
    }

    fun runOnUiThread(runnable: Runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run()
        } else {
            post(runnable)
        }
    }

    companion object {
        fun getInstance()=MainHandler.holder
    }
}