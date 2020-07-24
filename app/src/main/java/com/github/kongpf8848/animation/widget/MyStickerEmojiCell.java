package com.github.kongpf8848.animation.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.helper.EmojiUtils;
import com.kongpf.commonhelper.ScreenHelper;

import org.telegram.ui.Components.RLottieImageView;

public class MyStickerEmojiCell extends FrameLayout {
    private RLottieImageView imageView;
    private StickerItem sticker;
    private Object parentObject;
    private TextView emojiTextView;
    private float alpha = 1;
    private boolean changingAlpha;
    private long lastUpdateTime;
    private boolean scaled;
    private float scale;
    private long time;
    private boolean recent;
    private static AccelerateInterpolator interpolator = new AccelerateInterpolator(0.5f);

    public MyStickerEmojiCell(Context context) {
        this(context,null);

    }

    public MyStickerEmojiCell(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        imageView = new RLottieImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setAutoRepeat(true);
        addView(imageView, new FrameLayout.LayoutParams(ScreenHelper.dp2px(getContext(),66), ScreenHelper.dp2px(getContext(),66), Gravity.CENTER));

        emojiTextView = new TextView(context);
        emojiTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        emojiTextView.setTextColor(Color.BLACK);
        addView(emojiTextView, new FrameLayout.LayoutParams(ScreenHelper.dp2px(getContext(),28), ScreenHelper.dp2px(getContext(),28), Gravity.BOTTOM | Gravity.RIGHT));
        setFocusable(true);
    }

    public StickerItem getSticker() {
        return sticker;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean value) {
        recent = value;
    }


    public void setSticker(StickerItem document, Object parent, boolean showEmoji) {
        if (document != null) {
            sticker = document;
            parentObject = parent;
            EmojiUtils.loadImageFromAsset(getContext(),document.getFile(),imageView, ScreenHelper.dp2px(getContext(),66),ScreenHelper.dp2px(getContext(),66));
            setText(showEmoji,document.getName());
        }
    }

    public void setText(boolean showEmoji,String text){
        if (showEmoji) {
            if (!TextUtils.isEmpty(text)) {
                emojiTextView.setText(text);
                emojiTextView.setVisibility(VISIBLE);
            }
            else {
                emojiTextView.setVisibility(INVISIBLE);
            }
        } else {
            emojiTextView.setVisibility(INVISIBLE);
        }
    }

    public void setScaled(boolean value) {
        scaled = value;
        lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public boolean isDisabled() {
        return changingAlpha;
    }

    public RLottieImageView getImageView() {
        return imageView;
    }

    @Override
    public void invalidate() {
        emojiTextView.invalidate();
        super.invalidate();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result = super.drawChild(canvas, child, drawingTime);
        if (child == imageView && (changingAlpha || scaled && scale != 0.8f || !scaled && scale != 1.0f)) {
            long newTime = System.currentTimeMillis();
            long dt = (newTime - lastUpdateTime);
            lastUpdateTime = newTime;
            if (changingAlpha) {
                time += dt;
                if (time > 1050) {
                    time = 1050;
                }
                alpha = 0.5f + interpolator.getInterpolation(time / 1050.0f) * 0.5f;
                if (alpha >= 1.0f) {
                    changingAlpha = false;
                    alpha = 1.0f;
                }
                imageView.setAlpha(alpha);
            } else if (scaled && scale != 0.8f) {
                scale -= dt / 400.0f;
                if (scale < 0.8f) {
                    scale = 0.8f;
                }
            } else {
                scale += dt / 400.0f;
                if (scale > 1.0f) {
                    scale = 1.0f;
                }
            }
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            imageView.invalidate();
            invalidate();
        }
        return result;
    }


}
