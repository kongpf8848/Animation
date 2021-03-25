package com.github.kongpf8848.animation.utils
import android.content.Context

object AssetUtils{

    /**
     * 读取Asset json文件,转成对应的bean
     */
    inline fun <reified T> readAssetFile(context: Context,fileName:String):T{
        val inputStream=context.assets.open(fileName)
        val json = String(inputStream.readBytes())
        inputStream.close()
        return GsonUtils.fromJson(json, T::class.java) as T
    }
}