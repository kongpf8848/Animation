package com.github.kongpf8848.animation.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

import com.github.kongpf8848.animation.R;


public class AnimLogoView extends View {
    private static final String DEFAULT_LOGO = "Animation";
    private static final int DEFAULT_TEXT_PADDING = 10;
    private static final int ANIM_LOGO_DURATION = 1500;
    private static final int ANIM_LOGO_GRADIENT_DURATION = 1500;
    private static final int ANIM_LOGO_TEXT_SIZE = 30;
    private static final int ANIM_LOGO_TEXT_COLOR = Color.BLACK;
    private static final int ANIM_LOGO_GRADIENT_COLOR = Color.YELLOW;
    private SparseArray<String> mLogoTexts = new SparseArray<>();
    private SparseArray<PointF> mQuietPoints = new SparseArray<>();
    private SparseArray<PointF> mRadonPoints = new SparseArray<>();
    private ValueAnimator mOffsetAnimator;
    private ValueAnimator mGradientAnimator;
    private Paint mPaint;
    private int mTextPadding;
    private int mTextColor;
    private int mTextSize;
    private float mOffsetAnimProgress;
    private int mOffsetDuration;
    private boolean isOffsetAnimEnd;
    private int mGradientDuration;
    private LinearGradient mLinearGradient;
    private int mGradientColor;
    private Matrix mGradientMatrix;
    private int mMatrixTranslate;
    private boolean isAutoPlay;
    private int mWidth, mHeight;
    private boolean isShowGradient;
    private int mLogoOffset;
    private Animator.AnimatorListener mGradientListener;

    public AnimLogoView(Context context) {
        this(context, null);
    }

    public AnimLogoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimLogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnimLogoView);
        String logoName = ta.getString(R.styleable.AnimLogoView_logoName);
        isAutoPlay = ta.getBoolean(R.styleable.AnimLogoView_autoPlay, true);
        isShowGradient = ta.getBoolean(R.styleable.AnimLogoView_showGradient, false);
        mOffsetDuration = ta.getInt(R.styleable.AnimLogoView_offsetAnimDuration, ANIM_LOGO_DURATION);
        mGradientDuration = ta.getInt(R.styleable.AnimLogoView_gradientAnimDuration, ANIM_LOGO_GRADIENT_DURATION);
        mTextColor = ta.getColor(R.styleable.AnimLogoView_textColor, ANIM_LOGO_TEXT_COLOR);
        mGradientColor = ta.getColor(R.styleable.AnimLogoView_gradientColor, ANIM_LOGO_GRADIENT_COLOR);
        mTextPadding = ta.getDimensionPixelSize(R.styleable.AnimLogoView_textPadding, DEFAULT_TEXT_PADDING);
        mTextSize = ta.getDimensionPixelSize(R.styleable.AnimLogoView_textSize, ANIM_LOGO_TEXT_SIZE);
        mLogoOffset = ta.getDimensionPixelOffset(R.styleable.AnimLogoView_verticalOffset, 0);
        ta.recycle();
        if (TextUtils.isEmpty(logoName)) {
            logoName = DEFAULT_LOGO;// default logo
        }
        fillLogoTextArray(logoName);
        initPaint();
        initOffsetAnimation();
    }

    // fill the text to array
    private void fillLogoTextArray(String logoName) {
        if (TextUtils.isEmpty(logoName)) {
            return;
        }
        if (mLogoTexts.size() > 0) {
            mLogoTexts.clear();
        }
        for (int i = 0; i < logoName.length(); i++) {
            char c = logoName.charAt(i);
            String s = String.valueOf(c);
            mLogoTexts.put(i, s);
        }
    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);

        }
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    // init the translation animation
    private void initOffsetAnimation() {
        if (mOffsetAnimator == null) {
            mOffsetAnimator = ValueAnimator.ofFloat(0, 1);
            mOffsetAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            mOffsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (mQuietPoints.size() <= 0 || mRadonPoints.size() <= 0) {
                        return;
                    }
                    mOffsetAnimProgress = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mOffsetAnimator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mGradientAnimator != null && isShowGradient) {
                        isOffsetAnimEnd = true;
                        mPaint.setShader(mLinearGradient);
                        mGradientAnimator.start();
                    }
                }
            });
        }
        mOffsetAnimator.setDuration(mOffsetDuration);
    }

    // init the gradient animation
    private void initGradientAnimation(int width) {
        if (width == 0) {
            Log.w(this.getClass().getSimpleName(), "The view has not measure, it will auto init later.");
            return;
        }
        if (mGradientAnimator == null) {
            mGradientAnimator = ValueAnimator.ofInt(0, 2 * width);
            if (mGradientListener != null) {
                mGradientAnimator.addListener(mGradientListener);
            }
            mGradientAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mMatrixTranslate = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mLinearGradient = new LinearGradient(-width, 0, 0, 0, new int[]{mTextColor, mGradientColor, mTextColor},
                    new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
            mGradientMatrix = new Matrix();
        }
        mGradientAnimator.setDuration(mGradientDuration);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == VISIBLE && isAutoPlay) {
            mOffsetAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        // release animation
        if (mOffsetAnimator != null && mOffsetAnimator.isRunning()) {
            mOffsetAnimator.cancel();
        }
        if (mGradientAnimator != null && mGradientAnimator.isRunning()) {
            mGradientAnimator.cancel();
        }
        super.onDetachedFromWindow();
    }

    /**
     * 监听offset动画状态
     *
     * @param listener AnimatorListener
     */
    public void addOffsetAnimListener(Animator.AnimatorListener listener) {
        mOffsetAnimator.addListener(listener);
    }

    /**
     * 监听gradient动画状态
     *
     * @param listener AnimatorListener
     */
    public void addGradientAnimListener(Animator.AnimatorListener listener) {
        mGradientListener = listener;
    }

    /**
     * 开启动画
     */
    public void startAnimation() {
        if (getVisibility() == VISIBLE) {
            if (mOffsetAnimator.isRunning()) {
                mOffsetAnimator.cancel();
            }
            isOffsetAnimEnd = false;
            mOffsetAnimator.start();
        } else {
            Log.w("AnimLogoView", "The view is not visible, not to play the animation .");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("JACK8", "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
        mWidth = w;
        mHeight = h;
        initLogoCoordinate();
        initGradientAnimation(w);
    }

    private void initLogoCoordinate() {
        if (mWidth == 0 || mHeight == 0) {
            Log.w(this.getClass().getSimpleName(), "The view has not measure, it will auto init later.");
            return;
        }


        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseY = mHeight / 2 - fontMetrics.top / 2 - fontMetrics.bottom / 2;
        float centerY = baseY + mLogoOffset;
        // calculate the final xy of the text
        float totalLength = 0;
        for (int i = 0; i < mLogoTexts.size(); i++) {
            String str = mLogoTexts.get(i);
            float currentLength = mPaint.measureText(str);
            if (i != mLogoTexts.size() - 1) {
                totalLength += currentLength + mTextPadding;
            } else {
                totalLength += currentLength;
            }
        }
        // the draw width of the logo must small than the width of this AnimLogoView
        if (totalLength > mWidth) {
            throw new IllegalStateException("The text of logoName is too large that this view can not display all text");
        }
        float startX = (mWidth - totalLength) / 2;
        if (mQuietPoints.size() > 0) {
            mQuietPoints.clear();
        }
        for (int i = 0; i < mLogoTexts.size(); i++) {
            String str = mLogoTexts.get(i);
            float currentLength = mPaint.measureText(str);
            mQuietPoints.put(i, new PointF(startX, centerY));
            startX += currentLength + mTextPadding;
        }
        // generate random start xy of the text
        if (mRadonPoints.size() > 0) {
            mRadonPoints.clear();
        }
        for (int i = 0; i < mLogoTexts.size(); i++) {
            mRadonPoints.put(i, new PointF((float) Math.random() * mWidth, (float) Math.random() * mHeight));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isOffsetAnimEnd) {// offset animation
            mPaint.setAlpha((int) Math.min(255, 255 * mOffsetAnimProgress + 100));
            for (int i = 0; i < mQuietPoints.size(); i++) {
                PointF quietP = mQuietPoints.get(i);
                PointF radonP = mRadonPoints.get(i);
                float x = radonP.x + (quietP.x - radonP.x) * mOffsetAnimProgress;
                float y = radonP.y + (quietP.y - radonP.y) * mOffsetAnimProgress;
                canvas.drawText(mLogoTexts.get(i), x, y, mPaint);
            }
        } else {// gradient animation
            for (int i = 0; i < mQuietPoints.size(); i++) {
                PointF quietP = mQuietPoints.get(i);
                canvas.drawText(mLogoTexts.get(i), quietP.x, quietP.y, mPaint);
            }
            mGradientMatrix.setTranslate(mMatrixTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
        }
    }

    /**
     * 设置logo名
     *
     * @param logoName logo名称
     */
    public void setLogoText(String logoName) {
        fillLogoTextArray(logoName);
        // if set the new logoName, should refresh the coordinate again
        initLogoCoordinate();
    }

    /**
     * 设置logo文字动效时长
     *
     * @param duration 动效时长
     */
    public void setOffsetAnimDuration(int duration) {
        mOffsetDuration = duration;
        initOffsetAnimation();
    }

    /**
     * 设置logo文字渐变动效时长
     *
     * @param duration 动效时长
     */
    public void setGradientAnimDuration(int duration) {
        mGradientDuration = duration;
        initGradientAnimation(mWidth);
    }

    /**
     * 设置logo文字渐变颜色
     *
     * @param gradientColor 渐变颜色
     */
    public void setGradientColor(int gradientColor) {
        this.mGradientColor = gradientColor;
    }

    /**
     * 设置是否显示logo文字渐变
     *
     * @param isShowGradient 是否显示logo渐变动效
     */
    public void setShowGradient(boolean isShowGradient) {
        this.isShowGradient = isShowGradient;
    }

    /**
     * 设置logo字体边距
     *
     * @param padding 字体边距
     */
    public void setTextPadding(int padding) {
        mTextPadding = padding;
        initLogoCoordinate();
    }

    /**
     * 设置logo字体颜色
     *
     * @param color 字体颜色
     */
    public void setTextColor(int color) {
        mTextColor = color;
        initPaint();
    }

    /**
     * 设置logo字体大小
     *
     * @param size 字体大小
     */
    public void setTextSize(int size) {
        mTextSize = size;
        initPaint();
    }
}
