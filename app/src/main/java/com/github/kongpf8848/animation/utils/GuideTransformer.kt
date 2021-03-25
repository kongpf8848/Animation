package com.github.kongpf8848.animation.utils

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.kongpf.commonhelper.ScreenHelper
import kotlin.math.atan2

class GuideTransformer(context: Context, private val mRadius: Int) : ViewPager.PageTransformer {
    private var mMaxRotate = 0f

    init {
        if (mRadius > 0) {
            mMaxRotate = (2.0 * Math.toDegrees(atan2((ScreenHelper.getScreenWidth(context) / 2).toDouble(), mRadius.toDouble()))).toFloat()
        }
    }

    override fun transformPage(page: View, position: Float) {
        if (mRadius == 0) return

        if (position < -1.0f) {
            page.rotation = -1.0f * mMaxRotate
            page.pivotX = page.width.toFloat()
            page.pivotY = page.height.toFloat()
            return
        }
        if (position <= 1.0f) {
            if (position < 0.0f) {
                page.pivotX = page.width * (0.5f + 0.5f * -position)
                page.pivotY = page.height.toFloat()
                page.rotation = position * mMaxRotate
                return
            }
            page.pivotX = 0.5f * page.width * (1.0f - position)
            page.pivotY = page.height.toFloat()
            page.rotation = position * mMaxRotate
            return
        }
        page.rotation = mMaxRotate
        page.pivotX = 0.toFloat()
        page.pivotY = page.height.toFloat()
    }

}