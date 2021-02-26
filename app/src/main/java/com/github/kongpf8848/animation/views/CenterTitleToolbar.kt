package com.github.kongpf8848.animation.views

import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.StyleRes
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat

class CenterTitleToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : Toolbar(context, attrs, defStyleAttr) {

    private var mTitleTextAppearance = 0
    private var mTitleTextColor: ColorStateList? = null
    private var mTitleTextView: TextView? = null
    private var mTitleText: CharSequence? = null

    init {

        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.Toolbar, defStyleAttr, 0)
        mTitleTextAppearance = ta.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0)
        mTitleText = ta.getText(R.styleable.Toolbar_title)
        if (!TextUtils.isEmpty(mTitleText)) {
            title = mTitleText
        }
        if (ta.hasValue(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(ta.getColorStateList(R.styleable.Toolbar_titleTextColor)!!)
        }
        ta.recycle()
    }

    override fun setTitle(title: CharSequence?) {
        if (!TextUtils.isEmpty(title)) {
            if (mTitleTextView == null) {
                mTitleTextView = AppCompatTextView(context).apply {
                    isSingleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                    addView(this, LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER))
                }
            }
            if (mTitleTextAppearance != 0) {
                mTitleTextView?.apply {
                    TextViewCompat.setTextAppearance(this,mTitleTextAppearance)
                }
            }
            if (mTitleTextColor != null) {
                mTitleTextView?.setTextColor(mTitleTextColor)
            }
        }
        else if(mTitleTextView!=null){
            removeView(mTitleTextView)
        }
        mTitleTextView?.text = title
        mTitleText = title
    }

    override fun getTitle(): CharSequence? {
        return mTitleText
    }

    override fun setTitleTextColor(@NonNull color: ColorStateList) {
        mTitleTextColor = color
        mTitleTextView?.setTextColor(color)
    }

    override fun setTitleTextAppearance(context: Context, @StyleRes resId: Int) {
        mTitleTextAppearance = resId
        mTitleTextView?.apply {
            TextViewCompat.setTextAppearance(this,resId)
        }
    }
}