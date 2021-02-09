
package com.github.kongpf8848.animation.hook

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * 通用的LayoutInflaterFactory
 */
class HookLayoutInflaterFactory(val activity: Activity, private val originLayoutInflater: LayoutInflater, private val callback: HookLayoutCallback?) : Factory2 {

    companion object {
        private const val TAG = "HookLayoutInflaterFacto"
        private val sClassPrefixList = arrayOf(
                "android.widget.",
                "android.webkit.",
                "android.app.",
                "android.view."
        )
        private val sSuccessClassNamePrefixMap = HashMap<String, String>()
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View {
        Log.d(TAG, "onCreateView() called with: parent = $parent, name = $name, context = $context, attrs = $attrs")
        var view: View? = null
        if (activity is AppCompatActivity) {
            view = activity.delegate.createView(parent, name, context, attrs)
        }
        if (view == null) {
            try {
                if (!name.contains(".")) {
                    if (sSuccessClassNamePrefixMap.containsKey(name)) {
                        view = originLayoutInflater.createView(name, sSuccessClassNamePrefixMap[name], attrs)
                    } else {
                        for (prefix in sClassPrefixList) {
                            view = originLayoutInflater.createView(name, prefix, attrs)
                            if (view != null) {
                                sSuccessClassNamePrefixMap[name] = prefix
                                break
                            }
                        }
                    }
                } else {
                    view = originLayoutInflater.cloneInContext(context).createView(name, null, attrs)
                }
            } catch (ignore: ClassNotFoundException) {
            } catch (e: Exception) {
                Log.e(TAG, "Failed to inflate view " + name + ",error: " + e.message)
            }
        }
        if (view != null) {
            callback?.onCreateViewCallback(parent, name, context, attrs, view)
        }
        return view!!
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View {
        return onCreateView(null, name, context, attrs)
    }


}