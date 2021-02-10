package com.github.kongpf8848.animation.views

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.github.kongpf8848.animation.bean.StickerItem
import com.github.kongpf8848.animation.utils.EmojiUtils.loadImageFromAsset
import com.kongpf.commonhelper.ScreenHelper
import org.telegram.ui.Components.RLottieImageView

class StickerEmojiCell @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var imageView: RLottieImageView? = null
    private var emojiTextView: TextView? = null
    private var sticker: StickerItem? = null

    init {
        imageView = RLottieImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setAutoRepeat(true)
        }
        addView(imageView, LayoutParams(ScreenHelper.dp2px(getContext(), 66f), ScreenHelper.dp2px(getContext(), 66f), Gravity.CENTER))
        emojiTextView = TextView(context).apply {
            textSize = 16f
            setTextColor(Color.BLACK)
        }
        addView(emojiTextView, LayoutParams(ScreenHelper.dp2px(getContext(), 28f), ScreenHelper.dp2px(getContext(), 28f), Gravity.BOTTOM or Gravity.RIGHT))
        isFocusable = true
    }


    fun setSticker(sticker: StickerItem, showEmoji: Boolean=true) {
        this.sticker = sticker
        loadImageFromAsset(context, sticker.file, imageView!!, ScreenHelper.dp2px(context, 66f), ScreenHelper.dp2px(context, 66f))
        setText(showEmoji, sticker.name)
    }

    fun setText(showEmoji: Boolean, textValue: String?) {
        emojiTextView?.apply {
            if (showEmoji) {
                if (!TextUtils.isEmpty(textValue)) {
                    text = textValue
                    visibility = View.VISIBLE
                } else {
                    visibility = View.INVISIBLE
                }
            } else {
                visibility = View.INVISIBLE
            }
        }

    }

    override fun invalidate() {
        emojiTextView?.invalidate()
        super.invalidate()
    }


}