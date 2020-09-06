package com.github.kongpf8848.animation.widget.popup;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.helper.EmojiUtils;
import com.kongpf.commonhelper.ScreenHelper;

import org.telegram.ui.Components.RLottieImageView;

public class StickerPreviewPopupWindow extends BasePopupWindow {

    private RLottieImageView lottieImageView;

    public StickerPreviewPopupWindow(Context context) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.popup_sticker_preview;
    }

    @Override
    protected void init() {
        super.init();
        lottieImageView=findViewById(R.id.lottieImageView);
    }

    public void setData(StickerItem stickerItem){
        EmojiUtils.loadImageFromAsset(mContext,stickerItem.getFile(),lottieImageView, ScreenHelper.dp2px(mContext,100),ScreenHelper.dp2px(mContext,100));
    }
}
