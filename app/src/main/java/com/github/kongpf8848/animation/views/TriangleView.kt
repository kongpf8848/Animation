package com.github.kongpf8848.animation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntDef
import com.github.kongpf8848.animation.R
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 三角形
 */
class TriangleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var strokePaint: Paint? = null
    private var path: Path? = null
    private var fillColor = 0
    private var strokeColor = 0
    private var mode = 0
    private val DEFAULT_WIDTH = 48
    private val DEFAULT_HEIGHT = 24
    private var mWidth = 0
    private var mHeight = 0

    @IntDef(INVERTED, REGULAR)
    @Retention(RetentionPolicy.SOURCE)
    annotation class ShapeMode

    companion object {
        /**
         * 倒三角
         */
        const val INVERTED = 0

        /**
         * 正三角
         */
        const val REGULAR = 1
    }


    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TriangleView)
        fillColor = typedArray.getColor(R.styleable.TriangleView_tlv_fill_color, Color.BLACK)
        strokeColor = typedArray.getColor(R.styleable.TriangleView_tlv_stroke_color, Color.GRAY)
        mode = typedArray.getInt(R.styleable.TriangleView_tlv_mode, INVERTED)

        typedArray.recycle()

        paint = Paint().apply {
            isAntiAlias = true
            color = fillColor
            style = Paint.Style.FILL
        }

        strokePaint = Paint().apply {
            isAntiAlias = true
            color = strokeColor
            style = Paint.Style.STROKE
        }

        path = Path()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measureSize(widthMeasureSpec, DEFAULT_WIDTH)
        mHeight = measureSize(heightMeasureSpec, DEFAULT_HEIGHT)
        setMeasuredDimension(mWidth, mHeight)
    }

    private fun measureSize(measureSpec: Int, defaultSize: Int): Int {
        var newSize = 0
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.AT_MOST -> newSize = Math.min(size, defaultSize)
            MeasureSpec.EXACTLY -> newSize = size
            MeasureSpec.UNSPECIFIED -> newSize = defaultSize
        }
        return newSize
    }

    fun setColor(color: Int) {
        this.fillColor = color
        paint?.color = color
        invalidate()
    }

    fun setMode(@ShapeMode mode: Int) {
        this.mode = mode
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawTriangle(canvas)
    }

    private fun drawTriangle(canvas: Canvas) {
        path?.run {
            if (mode == INVERTED) {
                moveTo(0f, 0f)
                lineTo(mWidth.toFloat(), 0f)
                lineTo(mWidth / 2.0f, mHeight.toFloat())
            } else {
                moveTo(mWidth / 2.0f, 0f)
                lineTo(0f, mHeight.toFloat())
                lineTo(mWidth.toFloat(), mHeight.toFloat())
            }
            close()
            canvas.drawPath(this, paint!!)
            canvas.drawPath(this, strokePaint!!)
        }

    }


}