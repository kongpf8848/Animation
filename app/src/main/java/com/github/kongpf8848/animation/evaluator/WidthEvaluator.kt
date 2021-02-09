package com.github.kongpf8848.animation.evaluator

import android.animation.IntEvaluator
import android.view.View

class WidthEvaluator(private val v: View) : IntEvaluator() {

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        val num = super.evaluate(fraction, startValue, endValue)
        val params = v.layoutParams
        params.width = num
        v.layoutParams = params
        return num
    }

}