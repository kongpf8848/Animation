package com.github.kongpf8848.animation.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.transformerstip.TransformersTip
import cn.bingoogolapple.transformerstip.gravity.ArrowGravity
import cn.bingoogolapple.transformerstip.gravity.TipGravity
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.StickerAdapter
import com.github.kongpf8848.animation.bean.StickerItem
import com.github.kongpf8848.animation.utils.EmojiUtils.loadImageFromAsset
import com.kongpf.commonhelper.ScreenHelper
import org.telegram.ui.Components.RLottieImageView

class StickerPreviewViewer {

    private var popupStickerWindow: TransformersTip? = null
    private var currentPosition = -1
    private var openPreviewRunnable: Runnable? = null

    private object StickerPreviewViewer {
        val holder = StickerPreviewViewer()
    }

    companion object {
        fun getInstance() = StickerPreviewViewer.holder
    }

    fun onTouch(event: MotionEvent, recyclerView: RecyclerView): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val view = recyclerView.findChildViewUnder(event.x, event.y) ?: return false
                val position = recyclerView.getChildAdapterPosition(view)
                if (position >= 0) {
                    if (position == currentPosition) {
                        return true
                    }
                    currentPosition = position
                    val adapter = recyclerView.adapter as StickerAdapter
                    val stickerItem = adapter.getItem(position)
                    if (openPreviewRunnable != null) {
                        MainHandler.getInstance().removeCallbacks(openPreviewRunnable!!)
                        openPreviewRunnable = null
                    }
                    openPreviewRunnable =
                        Runnable { showStickerPreview(view, recyclerView.context, stickerItem) }
                    MainHandler.getInstance().postDelayed(openPreviewRunnable!!, 200)
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                hideStickerPreview()
            }
        }

        return false
    }

    fun reset() {
        if (openPreviewRunnable != null) {
            MainHandler.getInstance().removeCallbacks(openPreviewRunnable!!)
            openPreviewRunnable = null
        }
        currentPosition = -1
    }

    private fun showStickerPreview(anchorView: View, context: Context, sticker: StickerItem) {
        if (popupStickerWindow != null) {
            popupStickerWindow?.dismissTip()
            popupStickerWindow = null
        }
        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        popupStickerWindow = object : TransformersTip(anchorView, R.layout.popup_sticker) {
            override fun initView(contentView: View) {
                val lottieImageView: RLottieImageView =
                    contentView.findViewById(R.id.lottieImageView)
                loadImageFromAsset(
                    context,
                    sticker.file,
                    lottieImageView,
                    ScreenHelper.dp2px(context, 100f),
                    ScreenHelper.dp2px(context, 100f)
                )
                setArrowOffsetXDp((contentView.width-anchorView.width)/2)
            }
        }.setTipGravity(TipGravity.TO_TOP_CENTER)
            .setArrowGravity(ArrowGravity.TO_BOTTOM_CENTER)
            .show()


    }

    private fun hideStickerPreview() {
        reset()
        popupStickerWindow?.apply {
            if (isShowing) {
                dismissTip()
            }
        }
    }


}