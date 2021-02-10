package com.github.kongpf8848.animation.views

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import com.airbnb.lottie.LottieAnimationView

class AnimationRadioView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LottieAnimationView(context, attrs, defStyleAttr), Checkable {

    private var checked = false

    override fun isChecked(): Boolean {
        return checked
    }

    override fun setChecked(checked: Boolean) {
        try {
            if (this.checked != checked) {
                this.checked = checked
                if (isAnimating) {
                    cancelAnimation()
                }
                if (checked) {
                    if (speed < 0.0f) {
                        reverseAnimationSpeed()
                    }
                    playAnimation()
                } else {
                    if (speed > 0.0f) {
                        reverseAnimationSpeed()
                    }
                    playAnimation()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun toggle() {
        isChecked = !checked
    }
}