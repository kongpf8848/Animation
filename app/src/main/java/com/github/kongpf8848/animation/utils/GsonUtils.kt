package com.github.kongpf8848.animation.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

object GsonUtils {

    private val gson = Gson()

    fun toJson(obj: Any?): String {
        var gsonString = ""
        if (obj != null && !TextUtils.isEmpty(obj.toString())) {
            gsonString = gson.toJson(obj)
        }
        return gsonString
    }

    fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        try {
            return gson.fromJson(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> fromJson(json: String?, type: Type): T = gson.fromJson(json, type)


}