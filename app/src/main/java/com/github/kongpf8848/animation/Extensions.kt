package com.github.kongpf8848.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.SharedPreferences
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.github.kongpf8848.animation.IlluColors.IlluColors

inline infix fun <reified E : Enum<E>, V> ((E) -> V).enumValueFrom(value: V): E? {
    return enumValues<E>().firstOrNull { this(it) == value }
}

inline fun <reified T : Enum<T>> enumValueOfOrNull(value: String?): T? {
    return value?.let { runCatching { enumValueOf<T>(it.uppercase()) }.getOrNull() }
}


fun SharedPreferences.transaction(block: SharedPreferences.Editor.() -> Unit) {
    with(edit()) {
        block(this)
        apply()
    }
}

fun LottieAnimationView.repeatFrame(firstFrame: Int, lastFrame: Int) {
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) = Unit

        override fun onAnimationEnd(animation: Animator) {
            removeAllAnimatorListeners()
            repeatCount = ValueAnimator.INFINITE
            setMinAndMaxFrame(firstFrame, lastFrame)
            playAnimation()
        }

        override fun onAnimationCancel(animation: Animator) = Unit

        override fun onAnimationRepeat(animation: Animator) = Unit
    })
}

fun LottieAnimationView.changePathColor(illuColors: IlluColors) {
    addValueCallback(illuColors.keyPath, LottieProperty.COLOR_FILTER) {
        SimpleColorFilter(illuColors.color)
    }
}

fun ViewPager2.removeOverScroll() {
    (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
}