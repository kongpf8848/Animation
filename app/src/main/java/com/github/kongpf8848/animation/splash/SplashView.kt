package com.github.kongpf8848.animation.splash

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.github.kongpf8848.animation.R

class SplashView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    var text: View? = null
    var pic: View? = null
    var certificate: View? = null
    var views: List<View>? = null
    private var animation: SplashAnimation? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        pic = findViewById(R.id.pic)
        text = findViewById(R.id.text)
        certificate = findViewById(R.id.certificate)
        views = emptyList()
    }


    fun startAnimation() {
        animation = SplashAnimation(this).apply {
            duration = 2000L
        }
        startAnimation(animation)
    }

    fun resetAnimation() {
        pic?.alpha = 0.0f
        text?.alpha = 0.0f
        certificate?.alpha = 0.0f
        views?.forEach { view ->
            view.alpha = 0.0f
        }
        animation?.cancel()
    }
}