package com.github.kongpf8848.animation.activity.viewpager;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/8/28 下午3:03
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/8/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class ParallaxViewTag {
    public int index;
    public float xIn;
    public float xOut;
    public float yIn;
    public float yOut;
    protected float alphaIn;
    protected float alphaOut;

    @Override
    public String toString() {
        return "ParallaxViewTag{" +
                "index=" + index +
                ", xIn=" + xIn +
                ", xOut=" + xOut +
                ", yIn=" + yIn +
                ", yOut=" + yOut +
                '}';
    }
}
