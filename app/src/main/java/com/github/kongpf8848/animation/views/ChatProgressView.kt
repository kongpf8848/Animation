package com.github.kongpf8848.animation.views

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import com.github.kongpf8848.animation.R
import java.lang.Math.min
import kotlin.properties.Delegates.observable

class ChatProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val defaultBgColor: Int = getColor(context, R.color.chat_progress_bg)
    private val defaultBgStrokeColor: Int = getColor(context, R.color.chat_progress_bg_stroke)
    private val defaultProgressColor: Int = getColor(context, R.color.white)

    private val progressPadding = context.resources.getDimension(R.dimen.chat_progress_padding)

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = defaultBgColor
    }
    private val bgStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = defaultBgStrokeColor
        strokeWidth = context.resources.getDimension(R.dimen.chat_progress_bg_stroke_width)
    }
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = defaultProgressColor
        strokeWidth = context.resources.getDimension(R.dimen.chat_progress_stroke_width)
    }

    @FloatRange(from = .0, to = 1.0, toInclusive = false)
    var progress: Float = 0f
        set(value) {
            field = when {
                value < 0f -> 0f
                value > 1f -> 1f
                else -> value
            }
            sweepAngle = convertToSweepAngle(field)
        }

    private var currentAngle: Float by observable(0f) { _, _, _ -> invalidate() }
    private var sweepAngle: Float by observable(MIN_SWEEP_ANGLE) { _, _, _ -> invalidate() }

    private val progressRect: RectF = RectF()
    private var bgRadius: Float = 0f
    private var animation: Animator? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ChatProgressView).run {
            bgPaint.color = getColor(R.styleable.ChatProgressView_bgColor, defaultBgColor)
            bgStrokePaint.color = getColor(R.styleable.ChatProgressView_bgStrokeColor, defaultBgStrokeColor)
            progressPaint.color = getColor(R.styleable.ChatProgressView_progressColor, defaultProgressColor)
            recycle()
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (isDeepVisible()) {
            startAnimation()
        } else {
            stopAnimation()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    override fun onDetachedFromWindow() {
        stopAnimation()
        super.onDetachedFromWindow()
    }

    private fun startAnimation() {
        if (!isAttachedToWindow || !isDeepVisible()){
            return
        }
        if (animation == null) {
            animation = createAnimation().apply {
                start()
            }
        }
    }


    private fun stopAnimation() {
        animation?.cancel()
        animation = null
    }

    private fun isDeepVisible(): Boolean {
        var isVisible = isVisible
        var parent = parentView
        while (parent != null && isVisible) {
            isVisible = isVisible && parent.isVisible
            parent = parent.parentView
        }
        return isVisible
    }

    private val View.parentView: ViewGroup? get() = parent as? ViewGroup

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val horizHalf = (measuredWidth - paddingLeft - paddingRight) / 2f
        val vertHalf = (measuredHeight - paddingTop - paddingBottom) / 2f
        bgRadius = min(horizHalf, vertHalf)
        val progressOffset = progressPadding + progressPaint.strokeWidth / 2f
        val progressRectMinSize = 2 * (min(horizHalf, vertHalf) - progressOffset)
        progressRect.apply {
            left = (measuredWidth - progressRectMinSize) / 2f
            top = (measuredHeight - progressRectMinSize) / 2f
            right = (measuredWidth + progressRectMinSize) / 2f
            bottom = (measuredHeight + progressRectMinSize) / 2f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        with(canvas) {
            drawCircle(progressRect.centerX(), progressRect.centerY(), bgRadius , bgPaint)
            drawCircle(progressRect.centerX(), progressRect.centerY(), bgRadius- bgStrokePaint.strokeWidth / 2f, bgStrokePaint)
            drawArc(progressRect, currentAngle, sweepAngle, false, progressPaint)
        }
    }

    private fun createAnimation(): Animator =
        ValueAnimator.ofFloat(currentAngle, currentAngle + MAX_ANGLE).apply {
            interpolator = LinearInterpolator()
            duration = SPIN_DURATION_MS
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { currentAngle = normalize(it.animatedValue as Float) }
        }


    private fun normalize(angle: Float): Float {
        val decimal = angle - angle.toInt()
        return (angle.toInt() % MAX_ANGLE) + decimal
    }

    private fun convertToSweepAngle(progress: Float): Float =
        MIN_SWEEP_ANGLE + progress * (MAX_ANGLE - MIN_SWEEP_ANGLE)

    private companion object {
        const val TAG = "ChatProgressView"
        const val SPIN_DURATION_MS = 2_000L
        const val MIN_SWEEP_ANGLE = 30f
        const val MAX_ANGLE = 360
    }
}