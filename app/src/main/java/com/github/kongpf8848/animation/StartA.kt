package com.github.kongpf8848.animation

import android.content.Context
import android.util.Log
import androidx.startup.Initializer


class StartA : Initializer<Unit> {
    override fun create(context: Context) {
        Log.d("JACK9", "StartA create")

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.d("JACK9", "StartA dependencies()")
        return mutableListOf()
    }
}