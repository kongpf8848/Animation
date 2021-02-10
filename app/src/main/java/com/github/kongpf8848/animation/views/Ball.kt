package com.github.kongpf8848.animation.views

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import com.github.kongpf8848.animation.evaluator.PointFEvaluator

class Ball @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    companion object {
        const val RADIUS = 50f
    }

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            isAntiAlias = true
            color = Color.BLUE
        }
    }

    private var currentPoint: PointF? = null

    private var mColor: String? = null

    fun getColor(): String? {
        return mColor
    }

    fun setColor(color: String) {
        this.mColor = color;
        this.mPaint.color = Color.parseColor(color)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (currentPoint == null) {
            currentPoint = PointF((width / 2).toFloat(), RADIUS)
            drawCircle(canvas)
            startAnimation()
        } else {
            drawCircle(canvas)
        }
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(currentPoint!!.x, currentPoint!!.y, RADIUS, mPaint)
    }

    private fun startAnimation() {
        val startPoint = PointF((width / 2).toFloat(), RADIUS)
        val endPoint = PointF((width / 2).toFloat(), height - RADIUS)
        val anim1 = ValueAnimator.ofObject(PointFEvaluator(), startPoint, endPoint).apply {
            interpolator = BounceInterpolator()
            addUpdateListener { animation: ValueAnimator ->
                currentPoint = animation.animatedValue as PointF
                invalidate()
            }
        }
        val anim2=ObjectAnimator.ofObject(ArgbEvaluator(),Color.BLUE,Color.RED).apply {
            addUpdateListener {
                mPaint.color = it.animatedValue as Int
                invalidate()
            }
        }

        val animSet = AnimatorSet()
        animSet.play(anim1).with(anim2)
        animSet.duration = 2000
        animSet.start()
    }
}
