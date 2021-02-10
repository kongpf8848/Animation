package com.github.kongpf8848.animation.views.popup

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.bean.StickerItem
import com.github.kongpf8848.animation.utils.EmojiUtils.loadImageFromAsset
import com.github.kongpf8848.animation.views.TriangleView
import com.kongpf.commonhelper.ScreenHelper
import org.telegram.ui.Components.RLottieImageView

class StickerPreviewPopupWindow(context: Context) : BasePopupWindow(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

    private var lottieImageView: RLottieImageView? = null
    private var triangleView: TriangleView? = null

    override fun getLayoutId(): Int {
        return R.layout.popup_sticker_preview
    }

    override fun init() {
        super.init()
        lottieImageView = findViewById(R.id.lottieImageView)
        triangleView = findViewById(R.id.tlv_indicator)
    }

    fun setData(sticker: StickerItem) {
        loadImageFromAsset(mContext, sticker.file, lottieImageView!!, ScreenHelper.dp2px(mContext, 100f), ScreenHelper.dp2px(mContext, 100f))
    }

    val triangleViewWidth: Int
        get() = if (triangleView != null) {
            triangleView!!.measuredWidth
        } else 0

    fun setTriangleViewLayoutParams(gravity: Int, margin: Int) {
        if (triangleView != null) {
            val layoutParams = triangleView!!.layoutParams as LinearLayout.LayoutParams
            layoutParams.gravity = gravity
            layoutParams.leftMargin = 0
            layoutParams.rightMargin = 0
            if (gravity == Gravity.START) {
                layoutParams.leftMargin = margin
            } else if (gravity == Gravity.END) {
                layoutParams.rightMargin = margin
            }
            triangleView!!.layoutParams = layoutParams
        }
    }
}