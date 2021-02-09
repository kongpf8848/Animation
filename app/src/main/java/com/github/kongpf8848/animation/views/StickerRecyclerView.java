package com.github.kongpf8848.animation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class StickerRecyclerView extends RecyclerView {

    private GestureDetectorCompat gestureDetector;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private class RecyclerListViewItemClickListener implements OnItemTouchListener {

        public RecyclerListViewItemClickListener(Context context) {
            gestureDetector=new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View childView = findChildViewUnder(e.getX(),e.getY());
                    if(childView != null && onItemClickListener != null){
                        onItemClickListener.onItemClick(childView,getChildAdapterPosition(childView));
                        return true;
                    }
                    return false;

                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = findChildViewUnder(e.getX(),e.getY());
                    if(childView != null && onItemLongClickListener != null){
                        onItemLongClickListener.onItemLongClick(childView,getChildAdapterPosition(childView));
                    }

                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            if(gestureDetector.onTouchEvent(e)){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    public StickerRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public StickerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StickerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        addOnItemTouchListener(new RecyclerListViewItemClickListener(getContext()));
    }
}
