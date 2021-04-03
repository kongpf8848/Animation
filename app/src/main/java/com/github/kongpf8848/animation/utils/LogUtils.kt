package com.github.kongpf8848.animation.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log

object LogUtils {

    private const val TAG_DEFAULT = "Animation"
    var debug = false
    private var context: Context? = null

    fun init(ctx: Context, isDebug: Boolean) {
        context = ctx.applicationContext
        debug = isDebug
    }

    fun v(msg: String?) {
        if (!debug) {
            return
        }
        v(TAG_DEFAULT, msg)
    }

    fun v(tag: String?, msg: String?) {
        if (!debug) {
            return
        }
        log(Log.VERBOSE, tag, msg)
    }

    fun d(msg: String?) {
        if (!debug) {
            return
        }
        d(TAG_DEFAULT, msg)
    }

    fun d(tag: String?, msg: String?) {
        if (!debug) {
            return
        }
        log(Log.DEBUG, tag, msg)
    }

    fun i(msg: String?) {
        if (!debug) {
            return
        }
        i(TAG_DEFAULT, msg)
    }

    fun i(tag: String?, msg: String?) {
        if (!debug) {
            return
        }
        log(Log.INFO, tag, msg)
    }

    fun w(msg: String?) {
        if (!debug) {
            return
        }
        w(TAG_DEFAULT, msg)
    }

    fun w(tag: String?, msg: String?) {
        if (!debug) {
            return
        }
        log(Log.WARN, tag, msg)
    }

    fun e(msg: String?) {
        if (!debug) {
            return
        }
        e(TAG_DEFAULT, msg)
    }

    fun e(tag: String?, msg: String?) {
        if (!debug) {
            return
        }
        log(Log.ERROR, tag, msg)
    }

    private fun log(priority: Int, tag: String?, msg: String?) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        val newTag=tag?:TAG_DEFAULT
        Log.println(priority, newTag, msg!!)
    }
}