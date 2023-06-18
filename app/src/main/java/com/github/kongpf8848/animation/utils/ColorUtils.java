package com.github.kongpf8848.animation.utils;

import android.graphics.Color;

public class ColorUtils {
    public static String colorToString(int color) {
        int a = (color >> 24) & 0xff;
        int r = (color >> 16) & 0xff;
        int g = (color >>  8) & 0xff;
        int b = (color) & 0xff;

        String str=String.format("%02X",a)+String.format("%02X",r)+String.format("%02X",g)+String.format("%02X",b);
        return str;
    }


}
