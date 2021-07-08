package com.github.kongpf8848.animation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.utils.LogUtils
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView

class ZiroomTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleList = arrayOf(
            R.string.ziroom_tab_home,
            R.string.ziroom_tab_search,
            R.string.ziroom_tab_service,
            R.string.ziroom_tab_lvju,
            R.string.ziroom_tab_my
    )

    private var selectedPosition = -1
    private val svgaImageViewList = mutableListOf<SVGAImageView>()
    private val imageViewList = mutableListOf<ImageView>()
    private val textViewList = mutableListOf<TextView>()
    private var onTabSelectedListener: ZiroomTabSelectedListener? = null


    companion object {
        const val TAG = "ZiroomTabView"
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_tab_ziroom, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        for (index in 0 until childCount) {
            val child = getChildAt(index) as RelativeLayout
            for (i in 0 until child.childCount) {
                val subChild = child.getChildAt(i)
                if (subChild is SVGAImageView) {
                    svgaImageViewList.add(subChild)
                } else if (subChild is ImageView) {
                    imageViewList.add(subChild)
                } else if (subChild is TextView) {
                    subChild.setText(titleList[index])
                    textViewList.add(subChild)
                }
            }

            child.setOnClickListener {
                setSelectedPosition(index)
            }
        }
    }

    /**
     * position:选中位置
     * invokeListener:是否需要触发OnTabSelectedListener
     */
    fun setSelectedPosition(position: Int, invokeListener: Boolean = true) {

        LogUtils.d(TAG, "selectedPosition:${selectedPosition},position:${position}")
        if (selectedPosition >= 0 && selectedPosition != position) {
            textViewList[selectedPosition].isSelected = false
            imageViewList[selectedPosition].visibility = View.VISIBLE
            imageViewList[selectedPosition].isSelected = false
            svgaImageViewList[selectedPosition].callback = null
            svgaImageViewList[selectedPosition].stopAnimation()
            svgaImageViewList[selectedPosition].visibility = View.GONE
            if (invokeListener) {
                onTabSelectedListener?.onTabUnselected(selectedPosition)
            }
        }
        textViewList[position].isSelected = true
        imageViewList[position].visibility = View.INVISIBLE
        svgaImageViewList[position].visibility = View.VISIBLE

        svgaImageViewList[position].callback = object : SVGACallback {
            override fun onFinished() {
                imageViewList[position].visibility = View.VISIBLE
                imageViewList[position].isSelected = true
            }

            override fun onPause() {
                LogUtils.d(TAG, "SVGACallback onPause() called")
            }

            override fun onRepeat() {
                LogUtils.d(TAG, "SVGACallback onRepeat() called")
            }

            override fun onStep(frame: Int, percentage: Double) {

            }
        }
        svgaImageViewList[position].stopAnimation()
        svgaImageViewList[position].stepToPercentage(0.0, true)
        if (selectedPosition == position) {
            if (invokeListener) {
                onTabSelectedListener?.onTabReselected(selectedPosition)
            }
        } else {
            selectedPosition = position
            if (invokeListener) {
                onTabSelectedListener?.onTabSelected(selectedPosition)
            }
        }

    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    fun getImageViewByPosition(position: Int): ImageView? {
        if (position >= 0 && position <= imageViewList.size) {
            return imageViewList[position]
        }
        return null
    }

    fun getTextViewByPosition(position: Int): TextView? {
        if (position >= 0 && position <= textViewList.size) {
            return textViewList[position]
        }
        return null
    }

    fun getSVGAImageViewByPosition(position: Int): SVGAImageView? {
        if (position >= 0 && position <= svgaImageViewList.size) {
            return svgaImageViewList[position]
        }
        return null
    }

    fun setOnTabSelectedListener(listener: ZiroomTabSelectedListener) {
        this.onTabSelectedListener = listener
    }

    interface ZiroomTabSelectedListener {
        fun onTabSelected(position: Int)
        fun onTabUnselected(position: Int)
        fun onTabReselected(position: Int)
    }

}