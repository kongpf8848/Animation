package com.github.kongpf8848.animation.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by pengf on 2017/3/23.
 */

public class PointFEvaluator implements TypeEvaluator<PointF> {



    public PointFEvaluator() {
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x=startValue.x+(endValue.x-startValue.x)*fraction;
        float y=startValue.y+(endValue.y-startValue.y)*fraction;
        return new PointF(x,y);

    }
}
