package com.github.kongpf8848.animation.views

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class StickerRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private var gestureDetector: GestureDetectorCompat? = null
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    init {
        addOnItemTouchListener(RecyclerListViewItemClickListener(getContext()))
    }

    private inner class RecyclerListViewItemClickListener(context: Context?) : OnItemTouchListener {

        init {
            gestureDetector = GestureDetectorCompat(context, object : SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val childView = findChildViewUnder(e.x, e.y)
                    if (childView != null && onItemClickListener != null) {
                        onItemClickListener!!.onItemClick(childView, getChildAdapterPosition(childView))
                        return true
                    }
                    return false
                }

                override fun onLongPress(e: MotionEvent) {
                    val childView = findChildViewUnder(e.x, e.y)
                    if (childView != null && onItemLongClickListener != null) {
                        onItemLongClickListener!!.onItemLongClick(childView, getChildAdapterPosition(childView))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return gestureDetector!!.onTouchEvent(e)
        }
        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    }


}