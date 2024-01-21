package com.github.kongpf8848.animation.utils

import android.os.Bundle
import com.github.kongpf8848.animation.TKApplication
import com.google.firebase.analytics.FirebaseAnalytics

object FirebaseUtils {

    fun logEvent(name: String, bundle: Bundle) {
        val instance = FirebaseAnalytics.getInstance(TKApplication.instance)
        instance.logEvent(name, bundle)
    }

}