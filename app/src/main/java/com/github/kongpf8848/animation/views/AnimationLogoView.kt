package com.github.kongpf8848.animation.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.PointFEvaluator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.github.kongpf8848.animation.R

class AnimationLogoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val mLogoTexts = SparseArray<String>()
    private val mQuietPoints = SparseArray<PointF>()
    private val mRandomPoints = SparseArray<PointF>()
    private var mOffsetAnimator: ValueAnimator? = null
    private var mGradientAnimator: ValueAnimator? = null
    private var mPaint: Paint? = null
    private var mTextPadding: Int
    private var mTextColor: Int
    private var mTextSize: Float
    private var mOffsetAnimProgress = 0f
    private var mOffsetDuration: Int
    private var isOffsetAnimEnd = false
    private var mGradientDuration: Int
    private var mLinearGradient: LinearGradient? = null
    private var mGradientColor: Int
    private var mGradientMatrix: Matrix? = null
    private var mMatrixTranslate = 0
    private val isAutoPlay: Boolean
    private var mWidth = 0
    private var mHeight = 0
    private var isShowGradient: Boolean
    private val mLogoOffset: Int
    private var mGradientListener: Animator.AnimatorListener? = null

    companion object {
        private const val DEFAULT_LOGO = "Animation"
        private const val DEFAULT_TEXT_PADDING = 10
        private const val ANIM_LOGO_DURATION = 1500
        private const val ANIM_LOGO_GRADIENT_DURATION = 1500
        private const val ANIM_LOGO_TEXT_SIZE = 30f
        private const val ANIM_LOGO_TEXT_COLOR = Color.BLACK
        private const val ANIM_LOGO_GRADIENT_COLOR = Color.YELLOW
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AnimationLogoView)
        var logoName = ta.getString(R.styleable.AnimationLogoView_logoName)
        isAutoPlay = ta.getBoolean(R.styleable.AnimationLogoView_autoPlay, true)
        isShowGradient = ta.getBoolean(R.styleable.AnimationLogoView_showGradient, false)
        mOffsetDuration = ta.getInt(R.styleable.AnimationLogoView_offsetAnimDuration, ANIM_LOGO_DURATION)
        mGradientDuration = ta.getInt(R.styleable.AnimationLogoView_gradientAnimDuration, ANIM_LOGO_GRADIENT_DURATION)
        mTextColor = ta.getColor(R.styleable.AnimationLogoView_textColor, ANIM_LOGO_TEXT_COLOR)
        mGradientColor = ta.getColor(R.styleable.AnimationLogoView_gradientColor, ANIM_LOGO_GRADIENT_COLOR)
        mTextPadding = ta.getDimensionPixelSize(R.styleable.AnimationLogoView_textPadding, DEFAULT_TEXT_PADDING)
        mTextSize = ta.getDimension(R.styleable.AnimationLogoView_textSize, ANIM_LOGO_TEXT_SIZE)
        mLogoOffset = ta.getDimensionPixelOffset(R.styleable.AnimationLogoView_verticalOffset, 0)
        ta.recycle()
        if (TextUtils.isEmpty(logoName)) {
            logoName = DEFAULT_LOGO
        }
        fillLogoTextArray(logoName)
        initPaint()
        initOffsetAnimation()
    }

    private fun fillLogoTextArray(logoName: String?) {
        if (TextUtils.isEmpty(logoName)) {
            return
        }
        if (mLogoTexts.size() > 0) {
            mLogoTexts.clear()
        }
        for (i in logoName!!.indices) {
            mLogoTexts.put(i, logoName[i].toString())
        }
    }

    private fun initPaint() {
        mPaint=Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            textSize = mTextSize
            color = mTextColor
        }
    }

    private fun initOffsetAnimation() {
        if (mOffsetAnimator == null) {
            mOffsetAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener(AnimatorUpdateListener { animation ->
                    if (mQuietPoints.size() <= 0 || mRandomPoints.size() <= 0) {
                        return@AnimatorUpdateListener
                    }
                    mOffsetAnimProgress = animation.animatedValue as Float
                    invalidate()
                })
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        if (mGradientAnimator != null && isShowGradient) {
                            isOffsetAnimEnd = true
                            mPaint?.shader = mLinearGradient
                            mGradientAnimator!!.start()
                        }
                    }
                })
                duration = mOffsetDuration.toLong()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (visibility == VISIBLE && isAutoPlay) {
            mOffsetAnimator?.start()
        }
    }

    override fun onDetachedFromWindow() {
        if (mOffsetAnimator != null && mOffsetAnimator!!.isRunning) {
            mOffsetAnimator!!.cancel()
        }
        if (mGradientAnimator != null && mGradientAnimator!!.isRunning) {
            mGradientAnimator!!.cancel()
        }
        super.onDetachedFromWindow()
    }


    fun addOffsetAnimListener(listener: Animator.AnimatorListener?) {
        mOffsetAnimator!!.addListener(listener)
    }

    fun addGradientAnimListener(listener: Animator.AnimatorListener?) {
        mGradientListener = listener
    }

    /**
     * 开启动画
     */
    fun startAnimation() {
        if (visibility == VISIBLE) {
            if (mOffsetAnimator!!.isRunning) {
                mOffsetAnimator!!.cancel()
            }
            isOffsetAnimEnd = false
            mOffsetAnimator!!.start()
        } else {
            Log.w("AnimLogoView", "The view is not visible, not to play the animation .")
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        initLogoCoordinate()
        initGradientAnimation()
    }

    private fun initLogoCoordinate() {
        if (mWidth == 0 || mHeight == 0) {
            return
        }
        val fontMetrics = mPaint!!.fontMetrics
        val baseY = mHeight / 2 - fontMetrics.top / 2 - fontMetrics.bottom / 2
        val centerY = baseY + mLogoOffset
        var totalLength = 0f
        for (i in 0 until mLogoTexts.size()) {
            val str = mLogoTexts[i]
            val currentLength = mPaint!!.measureText(str)
            totalLength += if (i != mLogoTexts.size() - 1) {
                currentLength + mTextPadding
            } else {
                currentLength
            }
        }
        check(totalLength <= mWidth) { "The text of logoName is too large that this view can not display all text" }
        var startX = (mWidth - totalLength) / 2
        if (mQuietPoints.size() > 0) {
            mQuietPoints.clear()
        }
        for (i in 0 until mLogoTexts.size()) {
            val str = mLogoTexts[i]
            val currentLength = mPaint!!.measureText(str)
            mQuietPoints.put(i, PointF(startX, centerY))
            startX += currentLength + mTextPadding
        }
        if (mRandomPoints.size() > 0) {
            mRandomPoints.clear()
        }
        for (i in 0 until mLogoTexts.size()) {
            mRandomPoints.put(i, PointF(Math.random().toFloat() * mWidth, Math.random().toFloat() * mHeight))
        }
    }

    private fun initGradientAnimation() {
        if (mWidth == 0 || mHeight == 0) {
            return
        }
        if (mGradientAnimator == null) {
            mGradientAnimator = ValueAnimator.ofInt(0, 2 * mWidth).apply {
                if (mGradientListener != null) {
                   addListener(mGradientListener)
                }
                addUpdateListener { animation ->
                    mMatrixTranslate = animation.animatedValue as Int
                    invalidate()
                }

                mLinearGradient = LinearGradient(-mWidth.toFloat(), 0f, 0f, 0f, intArrayOf(mTextColor, mGradientColor, mTextColor), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.CLAMP)
                mGradientMatrix = Matrix()
                duration = mGradientDuration.toLong()
            }

        }
    }

    override fun onDraw(canvas: Canvas) {
        if (!isOffsetAnimEnd) {
            mPaint!!.alpha = Math.min(255f, 255 * mOffsetAnimProgress + 100).toInt()
            for (i in 0 until mQuietPoints.size()) {
                val point=PointFEvaluator().evaluate(mOffsetAnimProgress,mRandomPoints[i],mQuietPoints[i])
                canvas.drawText(mLogoTexts[i],point.x, point.y, mPaint!!)
            }
        } else {
            for (i in 0 until mQuietPoints.size()) {
                val quietP = mQuietPoints[i]
                canvas.drawText(mLogoTexts[i], quietP.x, quietP.y, mPaint!!)
            }
            mGradientMatrix!!.setTranslate(mMatrixTranslate.toFloat(), 0f)
            mLinearGradient!!.setLocalMatrix(mGradientMatrix)
        }
    }


}