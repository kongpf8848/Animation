package com.github.kongpf8848.animation.utils

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.kongpf8848.animation.adapter.StickerAdapter
import com.github.kongpf8848.animation.bean.StickerItem
import com.github.kongpf8848.animation.views.popup.StickerPreviewPopupWindow
import com.kongpf.commonhelper.ScreenHelper

class StickerPreviewViewer {

    private var popupWindow: StickerPreviewPopupWindow? = null
    private var currentPosition = -1
    private var openPreviewRunnable: Runnable? = null

    private object StickerPreviewViewer {
        val holder = StickerPreviewViewer()
    }
    companion object {
        fun getInstance()= StickerPreviewViewer.holder
    }

    fun onTouch(event: MotionEvent, recyclerView: RecyclerView): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_MOVE ->{
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
                    openPreviewRunnable = Runnable { showStickerPreview(view, recyclerView.context, stickerItem) }
                    MainHandler.getInstance().postDelayed(openPreviewRunnable!!, 200)
                }
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL ->{
                hideStickerPreview()
            }
        }

        return false
    }

    private fun showStickerPreview(view: View, context: Context, stickerItem: StickerItem) {
        if (popupWindow == null) {
            popupWindow = StickerPreviewPopupWindow(context)
        }
        popupWindow?.apply {
            setData(stickerItem)
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            val OVERHANG_SIZE = ScreenHelper.dp2px(context, 10f)
            var anchorX = location[0] + (view.width -measuredWidth) / 2
            val anchorY = location[1] -measuredHeight
            Log.d("JACK8", "location,x:$anchorX,y=$anchorY")
            if (anchorX + measuredWidth + OVERHANG_SIZE > ScreenHelper.getScreenWidth(context)) {
                anchorX = ScreenHelper.getScreenWidth(context) - measuredWidth - OVERHANG_SIZE
                val margin = ScreenHelper.getScreenWidth(context) - OVERHANG_SIZE - location[0] - (view.width + triangleViewWidth) / 2
                setTriangleViewLayoutParams(Gravity.END, margin)
            } else if (anchorX < OVERHANG_SIZE) {
                anchorX = OVERHANG_SIZE
                val margin = location[0] + (view.width - triangleViewWidth) / 2 - OVERHANG_SIZE
                setTriangleViewLayoutParams(Gravity.START, margin)
            } else {
                setTriangleViewLayoutParams(Gravity.CENTER, 0)
            }
            if (!isShowing) {
                showAtLocation(view, Gravity.NO_GRAVITY, anchorX, anchorY)
            } else {
                update(anchorX, anchorY, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)
            }
        }

    }

    fun reset() {
        if (openPreviewRunnable != null) {
            MainHandler.getInstance().removeCallbacks(openPreviewRunnable!!)
            openPreviewRunnable = null
        }
        currentPosition = -1
    }

    private fun hideStickerPreview() {
        reset()
        popupWindow?.apply {
            if(isShowing){
                dismiss()
            }
        }
    }


}