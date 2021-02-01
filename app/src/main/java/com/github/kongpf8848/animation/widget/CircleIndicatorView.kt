package com.github.kongpf8848.animation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.viewpager.widget.ViewPager
import com.github.kongpf8848.animation.R
import java.lang.IllegalStateException
import kotlin.math.abs
import kotlin.math.ceil

/**
 * 圆点指示器
 */
class CircleIndicatorView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    private val TAG = "CircleIndicatorView"

    private var mCircles = mutableListOf<PointF>()
    private var mIndicator = PointF()
    private var mCount = 0
    private var mRadius = 0f
    private var mStrokeWidth = 0f
    private var mSpace = 0f
    private var mTouchable = false
    private var mIndicatorFollowScroll = false

    private var mDownX = 0f
    private var mDownY = 0f
    private var mTouchSlop = 0

    private var mCircleColor = 0
    private var mIndicatorColor = 0
    private var mSelectedPosition: Int = 0
    private val mCirclePaint = Paint()
    private val mIndicatorPaint = Paint()

    private var mViewPager:ViewPager?=null
    private var mOnCicleClickListener: OnCircleClickListener? = null

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView)

        mRadius = typedArray.getDimension(
                R.styleable.CircleIndicatorView_circleRadius,
                dp2px(context, 4f)
        )
        mStrokeWidth = typedArray.getDimension(
                R.styleable.CircleIndicatorView_circleStrokeWidth,
                dp2px(context, 1f)
        )
        mSpace = typedArray.getDimension(
                R.styleable.CircleIndicatorView_circleSpace,
                dp2px(context, 5f)
        )
        mCircleColor = typedArray.getColor(
                R.styleable.CircleIndicatorView_circleColor,
                Color.WHITE
        )
        mIndicatorColor = typedArray.getColor(
                R.styleable.CircleIndicatorView_indicatorColor,
                Color.WHITE
        )
        mTouchable = typedArray.getBoolean(R.styleable.CircleIndicatorView_circleTouchable, false)
        mIndicatorFollowScroll = typedArray.getBoolean(R.styleable.CircleIndicatorView_indicatorFollowScroll, true)

        typedArray.recycle()

        mCirclePaint.apply {
            isDither=true
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = mCircleColor
            strokeWidth = mStrokeWidth
        }
        mIndicatorPaint.apply {
            isDither=true
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            color = mIndicatorColor
            strokeWidth = mStrokeWidth
        }

        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = $widthMeasureSpec, heightMeasureSpec = $heightMeasureSpec")
        val width = ((mRadius + mStrokeWidth / 2) * 2 * mCount + mSpace * (mCount - 1)) + paddingLeft + paddingRight
        var height = measureHeight(heightMeasureSpec)
        if (height < 2 * (mRadius + mStrokeWidth / 2) + paddingTop + paddingBottom) {
            height = 2 * (mRadius + mStrokeWidth / 2 + paddingTop + paddingBottom)
        }
        setMeasuredDimension(ceil(width).toInt(), ceil(height).toInt())

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "onSizeChanged() called with: w = $w, h = $h, oldw = $oldw, oldh = $oldh")
        prepareCirclePoints()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout() called with: changed = $changed, left = $left, top = $top, right = $right, bottom = $bottom")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw() called with: canvas = $canvas")
        for (i in mCircles.indices) {
            val indicator = mCircles[i]
            canvas.drawCircle(indicator.x, indicator.y, mRadius, mCirclePaint)
        }
        if (mIndicator.x == 0f && mIndicator.y == 0f) {
            mIndicator.x = mCircles[mSelectedPosition].x
            mIndicator.y = mCircles[mSelectedPosition].y
        }
        if (mIndicator.x > 0f && mIndicator.y > 0f) {
            canvas.drawCircle(mIndicator.x, mIndicator.y, mRadius, mIndicatorPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mTouchable) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mDownX = event.x
                    mDownY = event.y
                    return true
                }
                MotionEvent.ACTION_UP ->
                    if (abs(event.x - mDownX) <= mTouchSlop && abs(event.y - mDownY) <= mTouchSlop) {
                        var max = Float.MAX_VALUE
                        var index = 0
                        for (i in mCircles.indices) {
                            val pointF=mCircles[i]
                            val offset = abs(pointF.x - event.x)
                            if (offset < max) {
                                max = offset
                                index = i
                            }
                        }
                        mOnCicleClickListener?.onCircleClick(index)
                    }
                else -> {
                }
            }
        }


        return super.onTouchEvent(event)
    }

    private fun measureHeight(heightMeasureSpec: Int): Float {
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        var result = 0f
        when (mode) {
            MeasureSpec.EXACTLY -> result = height.toFloat()
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> result = (mRadius * 2 + mStrokeWidth + paddingTop + paddingBottom)
            else -> {
            }
        }
        return result
    }

    private fun prepareCirclePoints() {
        mCircles.clear()
        var cx = 0f
        for (i in 0 until mCount) {
            val indicator = PointF()
            if (i == 0) {
                cx = mRadius + mStrokeWidth / 2 + paddingLeft
            } else {
                cx += (mRadius + mStrokeWidth / 2) * 2 + mSpace
            }
            indicator.x = cx
            indicator.y = measuredHeight / 2.toFloat()
            mCircles.add(indicator)
        }

    }



    interface OnCircleClickListener {
        fun onCircleClick(position: Int)
    }

    fun setOnCircleClickListener(onCircleClickListener: OnCircleClickListener) {
        mOnCicleClickListener = onCircleClickListener
    }

    private fun dp2px(context: Context, dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)

    /**
     * 绑定ViewPager
     */
    fun setUpWithViewPager(viewPager: ViewPager) {
        if(viewPager.adapter==null){
            throw IllegalStateException("you must setAdapter first!!!")
        }
        if (mViewPager != null) {
            mViewPager!!.removeOnPageChangeListener(this)
        }
        this.mViewPager = viewPager
        this.mViewPager!!.addOnPageChangeListener(this)
        if (viewPager.adapter != null) {
            mSelectedPosition = viewPager.currentItem
            mCount = viewPager.adapter!!.count
        }

    }

    override fun onPageScrollStateChanged(state: Int) {
        Log.d(TAG, "onPageScrollStateChanged() called with: state = $state")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.d(TAG, "onPageScrolled() called with: position = $position, positionOffset = $positionOffset, positionOffsetPixels = $positionOffsetPixels")
        if (mIndicatorFollowScroll) {
            if (mCircles.isEmpty()) {
                return
            }
            if (position >= 0 && (position + 1) < mViewPager!!.adapter!!.count) {
                mIndicator.x = mCircles[position].x + (mCircles[position + 1].x - mCircles[position].x) * positionOffset
                invalidate()
            }
        }
    }

    override fun onPageSelected(position: Int) {
        Log.d(TAG, "onPageSelected() called with: position = $position")
        mSelectedPosition = position
        mIndicator.x = mCircles[position].x
        mIndicator.y = mCircles[position].y
        invalidate()
    }
}

