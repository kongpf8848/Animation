package com.github.kongpf8848.animation.views.popup

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

abstract class BasePopupWindow @JvmOverloads constructor(protected var mContext: Context, width: Int = ViewGroup.LayoutParams.MATCH_PARENT, height: Int = ViewGroup.LayoutParams.MATCH_PARENT) : PopupWindow(mContext), View.OnClickListener {

    private val mPopView: View?
    var measuredWidth = 0
    var measuredHeight = 0

    abstract fun getLayoutId(): Int

    init {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mPopView = inflater.inflate(getLayoutId(), null, false)
        updateMeasureInfo()
        this.contentView = mPopView
        this.isFocusable = true
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidth(width)
        setHeight(height)
        init()
    }

    protected fun <T> findViewById(id: Int): T? {
        var view: T? = null
        if (mPopView != null) {
            view = mPopView.findViewById<View>(id) as T
        }
        return view
    }

    protected open fun init() { }

    private fun updateMeasureInfo() {
        mPopView!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        measuredWidth = mPopView.measuredWidth
        measuredHeight = mPopView.measuredHeight
    }

    override fun onClick(v: View) {}



}