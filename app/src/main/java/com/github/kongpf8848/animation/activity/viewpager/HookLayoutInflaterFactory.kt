
package com.github.kongpf8848.animation.activity.viewpager

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.util.*

/**
 * 通用的LayoutInflaterFactory
 */
class HookLayoutInflaterFactory(activity: Activity, originLayoutInflater: LayoutInflater, callback: HookLayoutCallback?) : Factory2 {

    private val mActivityWeakReference: WeakReference<Activity>
    private val mOriginLayoutInflater: LayoutInflater
    private val mCallback: HookLayoutCallback?

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View {
        val activity = mActivityWeakReference.get()
        var view: View? = null
        if (activity is AppCompatActivity) {
            view = activity.delegate.createView(parent, name, context, attrs)
        }
        if (view == null) {
            try {
                if (!name.contains(".")) {
                    if (sSuccessClassNamePrefixMap.containsKey(name)) {
                        view = mOriginLayoutInflater.createView(name, sSuccessClassNamePrefixMap[name], attrs)
                    } else {
                        for (prefix in sClassPrefixList) {
                            view = mOriginLayoutInflater.createView(name, prefix, attrs)
                            if (view != null) {
                                sSuccessClassNamePrefixMap[name] = prefix
                                break
                            }
                        }
                    }
                } else {
                    view = mOriginLayoutInflater.cloneInContext(context).createView(name, null, attrs)
                }
            } catch (ignore: ClassNotFoundException) {
            } catch (e: Exception) {
                Log.e(TAG, "Failed to inflate view " + name + ",error: " + e.message)
            }
        }
        if (view != null) {
            mCallback?.onCreateViewCallback(parent, name, context, attrs, view)
        }
        return view!!
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View {
        return onCreateView(null, name, context, attrs)
    }

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

    init {
        mActivityWeakReference = WeakReference(activity)
        mOriginLayoutInflater = originLayoutInflater
        mCallback = callback
    }
}