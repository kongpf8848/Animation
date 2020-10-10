package com.github.kongpf8848.animation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

import com.github.kongpf8848.animation.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 三角形
 */
public class TriangleView extends View{

    private Paint paint;
    private Paint strokePaint;
    private Path path;
    private int color;
    private int strokeColor;
    private int mode;

    private final int DEFAULT_WIDTH=48;
    private final int DEFAULT_HEIGHT=24;

    private int width = 0;
    private int height =0;

    /**
     * 倒三角
     */
    public static final int INVERTED = 0;
    /**
     * 正三角
     */
    public static final int REGULAR = 1;

    @IntDef({INVERTED, REGULAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShapeMode {}

    public TriangleView(Context context) {
        this(context,null);
    }

    public TriangleView(Context context,  @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TriangleView);
        color = typedArray.getColor(R.styleable.TriangleView_tlv_fill_color, Color.BLACK);
        strokeColor = typedArray.getColor(R.styleable.TriangleView_tlv_stroke_color, Color.GRAY);
        mode = typedArray.getInt(R.styleable.TriangleView_tlv_mode, INVERTED);
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);

        path= new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureSize(widthMeasureSpec, DEFAULT_WIDTH);
        height = measureSize(heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(width, height);
    }

    private int measureSize(int measureSpec, int defaultSize) {
        int newSize = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                newSize = Math.min(size, defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                newSize = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                newSize = defaultSize;
                break;
        }
        return newSize;
    }

    public void setColor(int color){
        this.color=color;
        paint.setColor(color);
        invalidate();
    }

    public void setMode(@ShapeMode int mode){
        this.mode=mode;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTriangle(canvas);

    }

    private void drawTriangle(Canvas canvas) {
        if(mode==INVERTED) {
            path.moveTo(0f, 0f);
            path.lineTo(width, 0f);
            path.lineTo(width / 2.0f, height);
        }
        else {
            path.moveTo(width/2.0f,0f);
            path.lineTo(0,height);
            path.lineTo(width,height);
        }
        path.close();
        canvas.drawPath(path, paint);
        canvas.drawPath(path,strokePaint);

    }
}