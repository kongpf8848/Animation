package com.github.kongpf8848.animation

import android.content.Context
import android.util.Log
import androidx.startup.Initializer


class StartB: Initializer<Unit> {
    override fun create(context: Context) {
        Log.d("JACK9", "StartB create")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.d("JACK9", "StartB dependencies()")
        return mutableListOf()
    }
}