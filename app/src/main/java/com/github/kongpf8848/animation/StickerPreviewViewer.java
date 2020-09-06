package com.github.kongpf8848.animation;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.github.kongpf8848.animation.adapter.StickerAdapter;
import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.widget.popup.StickerPreviewPopupWindow;
import com.kongpf.commonhelper.ScreenHelper;

public class StickerPreviewViewer {

    private static volatile StickerPreviewViewer Instance = null;

    private StickerPreviewPopupWindow popupWindow;
    private int currentPosition=-1;
    private Runnable openPreviewRunnable;

    public static StickerPreviewViewer getInstance() {
        if (Instance == null) {
            synchronized (StickerPreviewViewer.class) {
                if (Instance == null) {
                    Instance = new StickerPreviewViewer();
                }
            }
        }
        return Instance;
    }

    public void reset() {
        if (openPreviewRunnable != null) {
            MainHandler.getInstance().removeCallbacks(openPreviewRunnable);
            openPreviewRunnable = null;
        }
        currentPosition=-1;
    }


    public boolean onTouch(MotionEvent event, final RecyclerView recyclerView) {
        int action=event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_MOVE) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            View view = recyclerView.findChildViewUnder(x, y);
            if (view == null) {
                return false;
            }
            int position = recyclerView.getChildAdapterPosition(view);
            Log.d("JACK8", "position:" + position);
            if(position>=0){
                if(position==currentPosition){
                    return true;
                }
                currentPosition=position;
                StickerAdapter adapter=(StickerAdapter)recyclerView.getAdapter();
                StickerItem stickerItem=adapter.getItem(position);
                if(openPreviewRunnable!=null){
                    MainHandler.getInstance().removeCallbacks(openPreviewRunnable);
                    openPreviewRunnable = null;
                }
                openPreviewRunnable=new Runnable() {
                    @Override
                    public void run() {
                        showStickerPreview(view,recyclerView.getContext(),stickerItem);
                    }
                };
                MainHandler.getInstance().postDelayed(openPreviewRunnable,200);


            }
        }
        else if(action==MotionEvent.ACTION_UP || action==MotionEvent.ACTION_CANCEL){
               hideStickerPreview();
              currentPosition=-1;
        }
       return false;
    }

    private void showStickerPreview(View view,Context context, StickerItem stickerItem){
        if(popupWindow==null){
            popupWindow=new StickerPreviewPopupWindow(context);
        }
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        popupWindow.setData(stickerItem);
        int[]location=new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]+(view.getWidth()-popupWindow.getMeasuredWidth())/2,
                location[1]- popupWindow.getMeasuredHeight());
    }

    private void hideStickerPreview(){
        reset();
        if(popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }
}
