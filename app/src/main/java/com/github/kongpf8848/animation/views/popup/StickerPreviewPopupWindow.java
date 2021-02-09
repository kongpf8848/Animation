package com.github.kongpf8848.animation.views.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.utils.EmojiUtils;
import com.github.kongpf8848.animation.views.TriangleView;
import com.kongpf.commonhelper.ScreenHelper;

import org.telegram.ui.Components.RLottieImageView;

public class StickerPreviewPopupWindow extends BasePopupWindow {

    private RLottieImageView lottieImageView;
    private TriangleView triangleView;

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
        triangleView=findViewById(R.id.tlv_indicator);
    }

    public void setData(StickerItem stickerItem){
        EmojiUtils.loadImageFromAsset(mContext,stickerItem.getFile(),lottieImageView, ScreenHelper.dp2px(mContext,100),ScreenHelper.dp2px(mContext,100));
    }

    public int getTriangleViewWidth(){
        if(triangleView!=null){
            int width=triangleView.getMeasuredWidth();
            return width;
        }
        return 0;
    }
    public void setTriangleViewLayoutParams(int gravity,int margin){
        if (triangleView != null) {
            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams)triangleView.getLayoutParams();
            layoutParams.gravity=gravity;
            layoutParams.leftMargin=0;
            layoutParams.rightMargin=0;
            if(gravity== Gravity.START){
                layoutParams.leftMargin=margin;
            }
            else if(gravity==Gravity.END) {
                layoutParams.rightMargin = margin;
            }

            triangleView.setLayoutParams(layoutParams);
        }
    }
}
