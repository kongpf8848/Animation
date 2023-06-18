package com.github.kongpf8848.animation.utils

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorInt
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt

object UiUtils {

    @ColorInt
    fun pointBetweenColors(@ColorInt from: Int, @ColorInt to: Int, percent: Float): Int {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            pointBetweenColors25(from, to, percent)
        } else {
            val fromColor = from.toColor()
            val toColor = to.toColor()
            Color.pack(
                pointBetweenColor(fromColor.red(), toColor.red(), percent),
                pointBetweenColor(fromColor.green(), toColor.green(), percent),
                pointBetweenColor(fromColor.blue(), toColor.blue(), percent),
                pointBetweenColor(fromColor.alpha(), toColor.alpha(), percent),
            ).toColorInt()
        }
    }

    private fun pointBetweenColor(from: Float, to: Float, percent: Float): Float = from + percent * (to - from)

    // TODO: Delete everything when API 25 is not supported anymore.
    private fun pointBetweenColors25(from: Int, to: Int, percent: Float): Int {
        data class Color(
            @ColorInt val a: Int,
            @ColorInt val r: Int,
            @ColorInt val g: Int,
            @ColorInt val b: Int,
        ) {
            fun toColorInt(): Int = (a shl 24) or (r shl 16) or (g shl 8) or b
        }

        fun toColor(@ColorInt color: Int): Color {
            val r = (color shr 16 and 0xff)
            val g = (color shr 8 and 0xff)
            val b = (color and 0xff)
            val a = (color shr 24 and 0xff)
            return Color(a, r, g, b)
        }

        fun pointBetweenColor(from: Int, to: Int, percent: Float): Int = (from + percent * (to - from)).toInt()

        val fromColor = toColor(from)
        val toColor = toColor(to)

        return Color(
            pointBetweenColor(fromColor.a, toColor.a, percent),
            pointBetweenColor(fromColor.r, toColor.r, percent),
            pointBetweenColor(fromColor.g, toColor.g, percent),
            pointBetweenColor(fromColor.b, toColor.b, percent),
        ).toColorInt()
    }

    fun animateColorChange(
        @ColorInt oldColor: Int,
        @ColorInt newColor: Int,
        duration: Long = 150L,
        animate: Boolean = false,
        applyColor: (color: Int) -> Unit,
    ): ValueAnimator? {
        return if (animate) {
            ValueAnimator.ofObject(ArgbEvaluator(), oldColor, newColor).apply {
                setDuration(duration)
                addUpdateListener { animator -> applyColor(animator.animatedValue as Int) }
                start()
            }
        } else {
            applyColor(newColor)
            null
        }
    }
}
