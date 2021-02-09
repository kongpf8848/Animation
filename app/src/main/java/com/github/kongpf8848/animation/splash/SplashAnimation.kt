package com.github.kongpf8848.animation.splash

import android.animation.IntEvaluator
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.github.kongpf8848.animation.TKApplication
import com.kongpf.commonhelper.ScreenHelper
import java.util.*

class SplashAnimation(private val mSplashView: SplashView) : Animation() {

    private val random = Random()
    private var textStart = 0
    private var textEnd = 0
    private var picStart = 0
    private var picEnd = 0

    var topViews = mutableListOf<TopView>()

    init {
        val screenWidth = ScreenHelper.getScreenWidth(TKApplication.instance)
        val textWidth = mSplashView.text?.measuredWidth?:0
        textStart = -(textWidth / 2)
        textEnd = (screenWidth - textWidth) / 2
        val picWidth = mSplashView.pic?.measuredWidth?:0
        picStart = -(picWidth / 2)
        picEnd = (screenWidth - picWidth) / 2
    }

    class TopView(var view: View, var startAlpha: Float) {
        val margin = ScreenHelper.dp2px(TKApplication.instance, 10.0f)
        val layoutParams: FrameLayout.LayoutParams = view.layoutParams as FrameLayout.LayoutParams
        val topMargin=layoutParams.topMargin

        fun doAnimation(alpha: Float) {
            val half = 0.5f
            val temp = alpha - startAlpha
            if (temp > half) {
                return
            }
            val fraction = temp * 2.0f
            view.alpha = fraction
            layoutParams.topMargin = ((1.0f - fraction) * margin + topMargin).toInt()
            view.layoutParams = layoutParams
        }

    }

    /**
     * 做透明度和位移动画
     * @param view
     * @param fraction
     * @param start
     * @param end
     */
    private fun alphaAnimation1(view: View?, fraction: Float, start: Int, end: Int) {
        if (view == null) {
            return
        }
        view.alpha = fraction
        val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
        layoutParams.rightMargin = IntEvaluator().evaluate(fraction,start,end)
        view.layoutParams = layoutParams
        Log.d("JACK8", "alphaAnimation called with:view=" + view + ", fraction = [" + fraction + "]" + ",rightMargin:" + layoutParams.rightMargin)

    }

    /**
     * 透明度和位移动画
     * @param alpha
     */
    private fun alphaAnimation2(alpha: Float) {
        if (mSplashView.views.isNullOrEmpty()) return
        if (alpha > 0.5f * topViews.size / mSplashView.views!!.size
                && (topViews.size < mSplashView.views!!.size)) {
            Log.d("JACK8", "alphaAnimation2:$alpha")
            addTopView(alpha)
        }
        topViews.forEach {
            it.doAnimation(alpha)
        }
    }

    /**
     * 缩放动画
     * @param fraction
     */
    private fun scalaAnimation(fraction: Float) {
        mSplashView.certificate?.apply {
            alpha = fraction
            scaleX = 2.0f - fraction
            scaleY = 2.0f - fraction
        }

    }

    private fun inTopView(view: View): Boolean {
       topViews.forEach {
           if (it.view === view) {
               return true
           }
       }
        return false
    }

    private fun addTopView(alpha: Float) {
        val count = mSplashView.views!!.size
        var j = 0
        j = random.nextInt(count)
        while (inTopView(mSplashView.views!![j])) {
            j = random.nextInt(count)
        }
        val topView = TopView(mSplashView.views!![j], alpha)
        topViews.add(topView)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        if (interpolatedTime <= 0.25f) {
            alphaAnimation1(mSplashView.text, interpolatedTime * 4.0f, textStart, textEnd)
        }
        if (interpolatedTime > 0.125f && interpolatedTime <= 0.375f) {
            alphaAnimation1(mSplashView.pic, 4.0f * (interpolatedTime - 0.125f), picStart, picEnd)
        }
        if (interpolatedTime > 0.375f) {
            scalaAnimation(1.6f * (interpolatedTime - 0.375f))
            alphaAnimation2(1.6f * (interpolatedTime - 0.375f))
        }
    }


}