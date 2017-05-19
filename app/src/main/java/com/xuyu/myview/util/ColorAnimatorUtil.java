package com.xuyu.myview.util;

/**
 * Created by Administrator on 2017/5/19.
 */

public class ColorAnimatorUtil
{

    public static int getProgressColor(int startColor, int endColor, float progress)
    {
        int alpha = getValue((startColor & 0xFF000000) >> 24, (endColor & 0xFF000000) >> 24, progress);
        int red = getValue((startColor & 0x00FF0000) >> 16, (endColor & 0x00FF0000) >> 16, progress);
        int green = getValue((startColor & 0x0000FF00) >> 8, (endColor & 0x0000FF00) >> 8, progress);
        int blue = getValue(startColor & 0x000000FF, endColor & 0x000000FF, progress);
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    private static int getValue(int start, int end, float progress)
    {
        return start + (int) ((end - start) * progress);
    }
}
