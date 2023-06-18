package com.github.kongpf8848.animation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.viewpager.widget.ViewPager
import com.github.kongpf8848.animation.R
import kotlin.random.Random


/**
 * 仿知乎日报指示符，带切换动画效果
 */
class CuteIndicator @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    private val TAG = "CuteIndicator"

    private var mIndicatorColor = Color.GRAY
    private var mIndicatorSelectedColor = Color.WHITE
    private var mIndicatorWidth = 0f
    private var mIndicatorHeight = 0f
    private var mIndicatorSelectedWidth = 0f
    private var mIndicatorMargin = 0f
    private var mIndicatorCount = 0
    private var mShowAnimation=true
    private var mIndicatorPaint: Paint = Paint()

    private var position = 0
    private var positionOffset = 0f

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CuteIndicator)
        mIndicatorColor = typedArray.getColor(R.styleable.CuteIndicator_IndicatorColor, Color.GRAY)
        mIndicatorSelectedColor = typedArray.getColor(R.styleable.CuteIndicator_IndicatorSelectedColor, Color.WHITE)
        mIndicatorWidth = typedArray.getDimension(R.styleable.CuteIndicator_IndicatorWidth, dp2px(5f))
        mIndicatorHeight = typedArray.getDimension(R.styleable.CuteIndicator_IndicatorHeight, dp2px(5f))
        mIndicatorSelectedWidth = typedArray.getDimension(R.styleable.CuteIndicator_IndicatorSelectedWidth, dp2px(20f))
        mIndicatorMargin = typedArray.getDimension(R.styleable.CuteIndicator_IndicatorMargin, dp2px(5f))
        mShowAnimation = typedArray.getBoolean(R.styleable.CuteIndicator_IndicatorShowAnimation, true)

        typedArray.recycle()

        mIndicatorPaint.apply {
            isDither = true
            isAntiAlias = true
            style = Paint.Style.FILL
            color = mIndicatorColor
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(mIndicatorCount>0) {
            val width = ((mIndicatorCount - 1) * (mIndicatorMargin + mIndicatorWidth) + mIndicatorSelectedWidth).toInt()
            setMeasuredDimension(width, mIndicatorHeight.toInt())
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw() called with: canvas = $canvas")
        if (mIndicatorCount <= 0) {
            return
        }
        var left=0f
        var right=0f
        if (position == (mIndicatorCount - 1) && positionOffset > 0f) {
            for (i in 0 until mIndicatorCount) {
                if(i==0){
                    left=0f
                    right=left+mIndicatorWidth+(mIndicatorSelectedWidth - mIndicatorWidth) * positionOffset
                    mIndicatorPaint.color = ColorUtils.blendARGB(mIndicatorColor, mIndicatorSelectedColor, positionOffset)
                }
                else if(i<position){
                    right=left+mIndicatorWidth
                    mIndicatorPaint.color = mIndicatorColor
                }
                else if(i==position){
                    right=i*(mIndicatorWidth+mIndicatorMargin)+mIndicatorSelectedWidth
                    mIndicatorPaint.color = ColorUtils.blendARGB(mIndicatorColor, mIndicatorSelectedColor, 1-positionOffset)
                }

                canvas.drawRoundRect(RectF(left, 0f, right, mIndicatorHeight), mIndicatorHeight / 2, mIndicatorHeight / 2, mIndicatorPaint)
                left=right+mIndicatorMargin
            }
        } else {

            for (i in 0 until mIndicatorCount) {
                if (i < position) {
                    left = i * (mIndicatorWidth + mIndicatorMargin)
                    right = left + mIndicatorWidth
                    mIndicatorPaint.color = mIndicatorColor
                } else if (i == position) {
                    left = i * (mIndicatorWidth + mIndicatorMargin)
                    right =
                        left + mIndicatorWidth + (mIndicatorSelectedWidth - mIndicatorWidth) * (1 - positionOffset)
                    mIndicatorPaint.color = ColorUtils.blendARGB(
                        mIndicatorColor,
                        mIndicatorSelectedColor,
                        1 - positionOffset
                    )
                } else if (i == (position + 1)) {
                    left =
                        (i - 1) * (mIndicatorMargin + mIndicatorWidth) + mIndicatorWidth + (mIndicatorSelectedWidth - mIndicatorWidth) * (1 - positionOffset) + mIndicatorMargin
                    right = i * (mIndicatorMargin + mIndicatorWidth) + mIndicatorSelectedWidth
                    mIndicatorPaint.color = ColorUtils.blendARGB(
                        mIndicatorColor,
                        mIndicatorSelectedColor,
                        positionOffset
                    )
                } else {
                    left =
                        (i - 1) * (mIndicatorWidth + mIndicatorMargin) + (mIndicatorSelectedWidth + mIndicatorMargin)
                    right = left + mIndicatorWidth
                    mIndicatorPaint.color = mIndicatorColor
                }
                canvas.drawRoundRect(
                    RectF(left, 0f, right, mIndicatorHeight),
                    mIndicatorHeight / 2,
                    mIndicatorHeight / 2,
                    mIndicatorPaint
                )

            }
        }

    }

    fun setUp(count:Int) {
        mIndicatorCount = count
        requestLayout()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if(mShowAnimation) {
            this.position = position
            this.positionOffset = positionOffset
            invalidate()
        }
    }

    override fun onPageSelected(position: Int) {
        if(!mShowAnimation){
            this.position=position
            this.positionOffset=0f
            invalidate()
        }
    }

    private fun dp2px(dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun setSelectedColor(color:Int){
        this.mIndicatorSelectedColor=color
        invalidate()
    }

}